package br.com.ec6.modular.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbTeam")
public class Team extends Basis{

    @Column(name = "Name")
    private String Name;

    @ManyToOne
    @JoinColumn(name = "Manager")
    private User Manager;

    @ManyToOne
    @JoinColumn(name = "Project")
    private Project project;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "team")
    private List<TeamMember> teamMembers = new ArrayList<TeamMember>();

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public User getManager() {
        return Manager;
    }

    public void setManager(User manager) {
        Manager = manager;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<TeamMember> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(List<TeamMember> teamMembers) {
        this.teamMembers = teamMembers;
    }

    @Override
    public String toString() {
        return getName() + " - " + getProject().getName();
    }
}
