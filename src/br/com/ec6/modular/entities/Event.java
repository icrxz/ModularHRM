package br.com.ec6.modular.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbEvent")
public class Event extends Basis {

    @Column(name = "Name")
    private String Name;
    @Column(name = "Description")
    private String Description;
    @Column(name = "Date")
    private LocalDateTime Date;
    @Column(name = "Location")
    private String Location;
    @Column(name = "Type")
    private String Type;

    @ManyToOne
    @JoinColumn(name = "ResponsibleTeamMember")
    private TeamMember ResponsibleTeamMember;

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

    public LocalDateTime getDate() {
        return Date;
    }

    public void setDate(LocalDateTime date) {
        Date = date;
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

}
