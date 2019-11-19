package br.com.ec6.modular.entities;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    private List<Team> Times = new ArrayList<>();

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
}
