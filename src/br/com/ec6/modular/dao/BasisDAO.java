package br.com.ec6.modular.dao;

import br.com.ec6.modular.entities.Basis;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public abstract class BasisDAO <E extends Basis> {
    private String Tabela;

    public final void setTabela(String tabela){
        Tabela = tabela;
    }

    public E Seleciona(int id){
        EntityManager manager = getConnection();
        manager.getTransaction().begin();
        E b = (E) manager.find(Basis.class, id);
        return b;
    }

    public E Localiza(){
        throw new UnsupportedOperationException("Implementar na classe filha.");
    }

    public List<E> SelecionaTodos(){
        throw new UnsupportedOperationException("Implementar na classe filha.");
    }

    public void Insere(E entidade){
        EntityManager manager = getConnection();
        manager.getTransaction().begin();
        manager.merge(entidade);
        manager.getTransaction().commit();
        manager.close();
    }

    public void Altera(E entidade){
        throw new UnsupportedOperationException("Implementar na classe filha.");
    }

    public void Exclui(E entidade){
        EntityManager manager = getConnection();
        manager.getTransaction().begin();
        manager.getTransaction().commit();
        manager.close();
    }

    public E preencheEntidade() {
        throw new UnsupportedOperationException("Implementar na classe filha.");
    }

    private final EntityManager getConnection(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ModularHRM");
        EntityManager manager = factory.createEntityManager();
        return manager;
    }
}