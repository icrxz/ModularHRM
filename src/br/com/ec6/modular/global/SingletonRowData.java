package br.com.ec6.modular.global;

import br.com.ec6.modular.model.Basis;

public class SingletonRowData {
    private static SingletonRowData single_instance = null;

    public static Basis RowData;

    private SingletonRowData(){}

    public static Integer getId(){
        if(RowData != null)
            return RowData.getId();
        else
            return 0;
    }

    public static SingletonRowData getInstance()
    {
        if(single_instance == null)
            single_instance = new SingletonRowData();

        return single_instance;
    }
}
