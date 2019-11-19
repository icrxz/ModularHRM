package br.com.ec6.modular.entities;

import javax.persistence.*;

@Entity
@Table(name = "tbTeam")
public class Team extends Basis{

    @Column(name = "Name")
    private String Name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Id")
    private Project project;

}
