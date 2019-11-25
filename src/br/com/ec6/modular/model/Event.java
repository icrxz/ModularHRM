package br.com.ec6.modular.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbEvent")
public class Event extends Basis {

    @Column(name = "Name")
    private String Name;
    @Column(name = "Description")
    private String Description;
    @Column(name = "DateStart")
    private LocalDateTime DateStart;
    @Column(name = "DateEnd")
    private LocalDateTime DateEnd;
    @Column(name = "Location")
    private String Location;
    @Column(name = "Type")
    private String Type;

    @ManyToOne
    @JoinColumn(name = "ResponsibleTeamMember")
    private TeamMember ResponsibleTeamMember;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "RelatedEvent")
    private List<Task> Tarefas = new ArrayList<>();

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public LocalDateTime getDateStart() {
        return DateStart;
    }

    public void setDateStart(LocalDateTime date) {
        DateStart = date;
    }

    public LocalDateTime getDateEnd() {
        return DateEnd;
    }

    public void setDateEnd(LocalDateTime dateEnd) {
        DateEnd = dateEnd;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public TeamMember getResponsibleTeamMember() {
        return ResponsibleTeamMember;
    }

    public void setResponsibleTeamMember(TeamMember responsibleTeamMember) {
        ResponsibleTeamMember = responsibleTeamMember;
    }

    public List<Task> getTarefas() {
        return Tarefas;
    }

    public void setTarefas(List<Task> tarefas) {
        Tarefas = tarefas;
    }

    @Override
    public String toString(){
        return getName() + " - " + getDateStart().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }

}
