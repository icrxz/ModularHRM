package br.com.ec6.modular.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbMember")
public class Member extends Basis {

    @Column(name = "Name")
    private String Name;
    @Column(name = "Role")
    private String Role;
    @Column(name = "Email")
    private String Email;
    @Column(name = "Phone")
    private String Phone;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "member")
    private List<TeamMember> teamMembers = new ArrayList<TeamMember>();

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
