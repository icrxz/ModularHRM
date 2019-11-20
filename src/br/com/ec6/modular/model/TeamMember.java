package br.com.ec6.modular.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbTeamMember")
public class TeamMember extends Basis{

    @Column(name = "Name")
    private String Name;

    @ManyToOne
    @JoinColumn(name = "Member")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "Team")
    private Team team;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "ResponsibleTeamMember")
    private List<Event> events = new ArrayList<Event>();

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
