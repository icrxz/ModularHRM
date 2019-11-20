package br.com.ec6.modular.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbProject")
public class Project extends Basis{

    @Column(name = "Name")
    private String Name;
    @Column(name = "CustomerName")
    private String CustomerName;
    @Column(name = "ProjectCompleted")
    private Boolean ProjectCompleted;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "project")
    private List<Team> Teams = new ArrayList<Team>();

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public List<Team> getTeams() {
        return Teams;
    }

    public void setTeams(List<Team> teams) {
        Teams = teams;
    }
}
