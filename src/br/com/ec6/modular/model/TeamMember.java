package br.com.ec6.modular.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbTeamMember")
public class TeamMember extends Basis{

    @ManyToOne
    @JoinColumn(name = "Member")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "Team")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "CreateById")
    private User CreateById;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "ResponsibleTeamMember")
    private List<Event> events = new ArrayList<Event>();


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
