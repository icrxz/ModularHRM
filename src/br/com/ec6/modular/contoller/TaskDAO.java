package br.com.ec6.modular.contoller;

import br.com.ec6.modular.global.SingletonUserLogged;
import br.com.ec6.modular.model.Task;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class TaskDAO extends BasisDAO{

    public TaskDAO(){
        setTabela("Task");
    }

    public List<Task> getAllTasks(){
        EntityManager em = getConnection();
        try {

            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Task> criteria = builder.createQuery(Task.class);
            Root<Task> root = criteria.from(Task.class);

            Predicate ativoC = builder.equal(root.get("CreateById"), SingletonUserLogged.UserLogged.getId());

            criteria.where(ativoC);

            List<Task> listTasks = em.createQuery(criteria).getResultList();

            return listTasks;
        }finally {
            em.close();
        }
    }
}
