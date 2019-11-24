package br.com.ec6.modular.contoller;

import br.com.ec6.modular.global.SingletonUserLogged;
import br.com.ec6.modular.model.TeamMember;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class TeamMemberDAO extends BasisDAO{

    public TeamMemberDAO(){
        setTabela("TeamMember");
    }

    public List<TeamMember> getAllTeamMember(){
        EntityManager em = getConnection();
        try {

            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<TeamMember> criteria = builder.createQuery(TeamMember.class);
            Root<TeamMember> root = criteria.from(TeamMember.class);

            Predicate ativoC = builder.equal(root.get("CreateById"), SingletonUserLogged.UserLogged.getId());

            criteria.where(ativoC);

            List<TeamMember> listTasks = em.createQuery(criteria).getResultList();

            return listTasks;
        }finally {
            em.close();
        }
    }
}
