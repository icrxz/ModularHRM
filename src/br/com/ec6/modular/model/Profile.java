package br.com.ec6.modular.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbProfile")
public class Profile extends Basis{

    @Column(name = "Name")
    private String Name;

    @Column(name = "PermissionLevel")
    private String PermissionLevel;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "profile")
    private List<User> Users = new ArrayList<User>();

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPermissionLevel() {
        return PermissionLevel;
    }

    public void setPermissionLevel(String permissionLevel) {
        PermissionLevel = permissionLevel;
    }

    @Override
    public String toString(){
        return getName();
    }
}
