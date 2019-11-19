package br.com.ec6.modular.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbTask")
public class Task extends Basis {

    private String Name;

    private String Description;

    private LocalDateTime DueDate;

    private boolean TaskCompleted;
}
