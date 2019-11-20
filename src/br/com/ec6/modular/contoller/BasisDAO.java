package br.com.ec6.modular.contoller;

import br.com.ec6.modular.model.Basis;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
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

    public List<E> SelecionaTodos(Class c){
        String queryStr = "SELECT * FROM " + Tabela;
        List<E> listObjects = new ArrayList<>();
        EntityManager manager = getConnection();
        Query query = manager.createQuery(queryStr);

        listObjects = query.getResultList();

        manager.close();

        return listObjects;
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

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ModularHRM");
        EntityManager manager = factory.createEntityManager();
        return manager;
    }
}