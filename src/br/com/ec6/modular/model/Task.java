package br.com.ec6.modular.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbTask")
public class Task extends Basis {

    @Column(name = "Name")
    private String Name;

    @Column(name = "Description")
    private String Description;

    @Column(name = "DueDate")
    private LocalDateTime DueDate;

    @Column(name = "TaskCompleted")
    private boolean TaskCompleted;

    @ManyToOne
    @JoinColumn(name = "AssignedTo")
    private TeamMember AssignedTo;

    @ManyToOne
    @JoinColumn(name = "RelatedEvent")
    private Event RelatedEvent;

    @ManyToOne
    @JoinColumn(name = "CreateById")
    private User CreateById;

    public LocalDateTime getDate() {
        return DueDate;
    }
}
