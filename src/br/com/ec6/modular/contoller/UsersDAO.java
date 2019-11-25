package br.com.ec6.modular.contoller;

import br.com.ec6.modular.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class UsersDAO extends BasisDAO {

    public UsersDAO(){
        setTabela("Users");
    }

    public User Login(String login, String senha){
        EntityManager em = getConnection();
        try {

            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<User> criteria = builder.createQuery(User.class);
            Root<User> root = criteria.from(User.class);

            Predicate loginC = root.get("UserName").in(login);
            Predicate senhaC = root.get("Password").in(senha);

            criteria.where(builder.and(loginC, senhaC));

            User u = em.createQuery(criteria).getSingleResult();
            em.close();

            return u;
        }catch (NoResultException nre){
            return null;
        }finally {
            em.close();
        }
    }

}
