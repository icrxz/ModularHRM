package br.com.ec6.modular.entities;

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
