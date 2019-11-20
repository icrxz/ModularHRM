package br.com.ec6.modular.global;

import br.com.ec6.modular.model.User;

public class SingletonUserLogged {

    private static SingletonUserLogged single_instance = null;

    public static User UserLogged;

    private SingletonUserLogged(){}

    public static Integer getId(){
        if(UserLogged != null)
            return UserLogged.getId();
        else
            return 0;
    }

    public static SingletonUserLogged getInstance()
    {
        if(single_instance == null)
            single_instance = new SingletonUserLogged();

        return single_instance;
    }
}
