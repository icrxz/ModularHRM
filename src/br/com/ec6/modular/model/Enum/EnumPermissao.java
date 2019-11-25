package br.com.ec6.modular.model.Enum;

public enum EnumPermissao {
    DIRETOR("Diretor"),
    GERENTE("Gerente"),
    ADMINISTRADOR("Administrador do Sistema");

    private String Desc;

    EnumPermissao(String desc){
        this.Desc = desc;
    }

    public String getDescricao(){
        return Desc;
    }
}
