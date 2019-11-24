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

    public LocalDateTime getDueDate() {
        return DueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        DueDate = dueDate;
    }

    public boolean isTaskCompleted() {
        return TaskCompleted;
    }

    public void setTaskCompleted(boolean taskCompleted) {
        TaskCompleted = taskCompleted;
    }

    public Member getAssignedTo() {
        return AssignedTo;
    }

    public void setAssignedTo(Member assignedTo) {
        AssignedTo = assignedTo;
    }

    public Event getRelatedEvent() {
        return RelatedEvent;
    }

    public void setRelatedEvent(Event relatedEvent) {
        RelatedEvent = relatedEvent;
    }
}
