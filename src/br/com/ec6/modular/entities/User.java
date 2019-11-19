package br.com.ec6.modular.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbUser")
public class User extends Basis {

    @Column(name = "Name")
    private String Name;
    @Column(name = "UserName")
    private String UserName;
    @Column(name = "Password")
    private String Password;
    @Column(name = "Email")
    private String Email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Id")
    private Profile profile;

    private List<Member> Members = new ArrayList<>();

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
