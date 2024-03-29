package br.com.ec6.modular.contoller;

import br.com.ec6.modular.global.SingletonDatabaseConnect;
import br.com.ec6.modular.global.SingletonUserLogged;
import br.com.ec6.modular.model.Basis;
import br.com.ec6.modular.model.User;
import org.hibernate.Criteria;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class BasisDAO <E extends Basis> {
    private String Tabela;

    protected final void setTabela(String tabela){
        Tabela = tabela;
    }

    public E Seleciona(int id, Class c){
        EntityManager manager = getConnection();
        manager.getTransaction().begin();
        E b = (E) manager.find(c, id);
        manager.close();
        return b;
    }

    public E Localiza(){
        throw new UnsupportedOperationException("Implementar na classe filha.");
    }

    public List<Basis> SelecionaTodos(Class cls){
        EntityManager em = getConnection();
                try{
                    CriteriaBuilder cb = em.getCriteriaBuilder();

                    CriteriaQuery<Basis> q = cb.createQuery(cls);
                    Root<Basis> root = q.from(cls);
                    q.select(root);

                    TypedQuery<Basis> query = em.createQuery(q);
                    List<Basis> results = query.getResultList();

                    return results;
                }
                finally {
                    em.close();
                }
    }

    public void Insere(E entidade){
        EntityManager manager = getConnection();
        manager.getTransaction().begin();
        manager.persist(entidade);
        manager.flush();
        manager.getTransaction().commit();
        manager.close();
    }

    public void Altera(E entidade){
        entidade.setLastModifiedbyId(SingletonUserLogged.getId());
        entidade.setLastModifiedDate(LocalDateTime.now());

        EntityManager manager = getConnection();
        manager.getTransaction().begin();
        manager.merge(entidade);
        manager.flush();
        manager.getTransaction().commit();
        manager.close();
    }

    public void Exclui(E entidade){
        EntityManager manager = getConnection();
        manager.getTransaction().begin();
        manager.remove(entidade);
        manager.getTransaction().commit();
        manager.close();
    }

    public E preencheEntidade() {
        throw new UnsupportedOperationException("Implementar na classe filha.");
    }

    protected final EntityManager getConnection(){
        SingletonDatabaseConnect sdc = SingletonDatabaseConnect.getInstance();
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ModularHRM", sdc.persistenceMap);

        EntityManager manager = factory.createEntityManager();
        return manager;
    }
}