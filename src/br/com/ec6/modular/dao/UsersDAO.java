package br.com.ec6.modular.dao;

import br.com.ec6.modular.entities.User;

import javax.persistence.EntityManager;
import java.util.List;

public class UsersDAO extends BasisDAO {

    public UsersDAO(){
        setTabela("Users");
    }

}
