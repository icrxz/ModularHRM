package br.com.ec6.modular.global;

import java.util.HashMap;
import java.util.Map;

public class SingletonDatabaseConnect {
    private static SingletonDatabaseConnect single_instance = null;

    public static Map<String, String> persistenceMap = null;

    private SingletonDatabaseConnect(){
        persistenceMap = new HashMap<String, String>();
        persistenceMap.put("hibernate.connection.url", "jdbc:sqlserver://CE642;instanceName=SQLEXPRESS;databaseName=ModularHRM");
        persistenceMap.put("javax.persistence.jdbc.user", "sa");
        persistenceMap.put("javax.persistence.jdbc.password", "To1edoDoBr@silED");
    }

    public static SingletonDatabaseConnect getInstance()
    {

        if(single_instance == null)
            single_instance = new SingletonDatabaseConnect();

        return single_instance;
    }
}
