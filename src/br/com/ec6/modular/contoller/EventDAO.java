package br.com.ec6.modular.contoller;

import br.com.ec6.modular.global.Utils;
import br.com.ec6.modular.model.Event;
import br.com.ec6.modular.model.Project;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

            Predicate ativoC = builder.greaterThanOrEqualTo(root.get("DateEnd"), LocalDateTime.now());

            criteria.where(ativoC);

            List<Event> listProjects = em.createQuery(criteria).getResultList();

            return listProjects;
        }finally {
            em.close();
        }
    }

    public Boolean validaEvento(Event e){
        EntityManager em = getConnection();
        try {

            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Event> criteria = builder.createQuery(Event.class);
            Root<Event> root = criteria.from(Event.class);

            Predicate onStart = builder.lessThanOrEqualTo(root.get("DateStart"), e.getDateStart());
            Predicate onEnd = builder.greaterThanOrEqualTo(root.get("DateEnd"), e.getDateStart());
            Predicate memberEvento = builder.equal(root.get("ResponsibleTeamMember"), e.getResponsibleTeamMember());

            criteria.where(builder.and(onStart, onEnd, memberEvento));

            Event result = em.createQuery(criteria).getSingleResult();

            if(result == null)
                return true;
            else
                return false;
        }catch (NoResultException nre){
            return true;
        }finally {
            em.close();
        }
    }

}
