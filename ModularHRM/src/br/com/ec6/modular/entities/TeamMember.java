package br.com.ec6.modular.entities;

import javax.persistence.*;

@Entity
@Table(name = "tbTeamMember")
public class TeamMember extends Basis{

    @Column(name = "Name")
    private String Name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Member")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Team")
    private Team team;
}
