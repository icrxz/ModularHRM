package br.com.ec6.modular.contoller;

import br.com.ec6.modular.model.Event;
import br.com.ec6.modular.model.Project;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;

public class EventDAO extends BasisDAO{

    public EventDAO(){
        setTabela("Event");
    }

    public List<Event> EventosAtivos(){
        EntityManager em = getConnection();
        try {

            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Event> criteria = builder.createQuery(Event.class);
            Root<Event> root = criteria.from(Event.class);

            Predicate ativoC = builder.lessThanOrEqualTo(root.get("Date"), LocalDateTime.now());

            criteria.where(ativoC);

            List<Event> listProjects = em.createQuery(criteria).getResultList();

            return listProjects;
        }finally {
            em.close();
        }
    }
}
