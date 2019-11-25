package br.com.ec6.modular.contoller;

import br.com.ec6.modular.model.Project;
import br.com.ec6.modular.model.User;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class ProjectDAO extends BasisDAO{

    public ProjectDAO(){
        setTabela("Profiles");
    }

    public List<Project> ProjetosAtivos(){
        EntityManager em = getConnection();
        try {

            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Project> criteria = builder.createQuery(Project.class);
            Root<Project> root = criteria.from(Project.class);

            Predicate ativoC = builder.equal(root.get("ProjectCompleted"), false);

            criteria.where(ativoC);

            List<Project> listProjects = em.createQuery(criteria).getResultList();

            return listProjects;
        }finally {
            em.close();
        }
    }
}
