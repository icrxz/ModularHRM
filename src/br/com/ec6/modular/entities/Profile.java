package br.com.ec6.modular.entities;

import sun.nio.cs.US_ASCII;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "profile")
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

}
