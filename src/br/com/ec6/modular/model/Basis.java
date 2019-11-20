package br.com.ec6.modular.model;

import br.com.ec6.modular.global.SingletonUserLogged;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class Basis {

    public Basis(){
        setCreatedDate(LocalDateTime.now());
        setLastModifiedDate(LocalDateTime.now());

        Integer usId = SingletonUserLogged.getInstance().getId();

        if (usId == null || usId == 0) {
            setCreateById(1);
            setLastModifiedbyId(1);
        }
        else{
            setCreateById(usId);
            setLastModifiedbyId(usId);
        }

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name="CreatedDate")
    private LocalDateTime CreatedDate;

    @Column(name="LastModifiedDate")
    private LocalDateTime LastModifiedDate;

    @Column(name = "CreateById")
    private Integer CreateById;

    @Column(name = "LastModifiedbyId")
    private Integer LastModifiedbyId;

    public Integer getId() {
        return Id;
    }

    private void setId(Integer id) {
        Id = id;
    }

    public LocalDateTime getCreatedDate() {
        return CreatedDate;
    }

    private void setCreatedDate(LocalDateTime createdDate) {
        CreatedDate = createdDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return LastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        LastModifiedDate = lastModifiedDate;
    }

    public int getCreateById() {
        return CreateById;
    }

    private void setCreateById(int createById) {
        CreateById = createById;
    }

    public int getLastModifiedbyId() {
        return LastModifiedbyId;
    }

    public void setLastModifiedbyId(int lastModifiedbyId) {
        LastModifiedbyId = lastModifiedbyId;
    }

}
