package br.com.ec6.modular.model;

import javax.persistence.*;

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
    @ManyToOne
    @JoinColumn(name = "Profile")
    private Profile profile;

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

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
