package br.com.ec6.modular.global;

import br.com.ec6.modular.entities.User;
import com.microsoft.sqlserver.jdbc.*;
public class SingletonUserLogged {

    private static SingletonUserLogged single_instance = null;

    public static User UserLogged;

    private SingletonUserLogged(){}

    public int getId(){
        return UserLogged.getId();
    }

    public static SingletonUserLogged getUserLogged()
    {
        if(single_instance == null)
            single_instance = new SingletonUserLogged();

        return single_instance;
    }
}
