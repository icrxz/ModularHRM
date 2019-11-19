package br.com.ec6.modular.entities;

import br.com.ec6.modular.global.SingletonUserLogged;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class Basis {

    public Basis(){
        setCreatedDate(LocalDateTime.now());
        setLastModifiedDate(LocalDateTime.now());
        //setCreateById(SingletonUserLogged.getUserLogged().getId());
        //setLastModifiedbyId(SingletonUserLogged.getUserLogged().getId());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int Id;

    @Column(name="CreatedDate")
    private LocalDateTime CreatedDate;

    @Column(name="LastModifiedDate")
    private LocalDateTime LastModifiedDate;

    @Column(name = "CreateById")
    private int CreateById;

    @Column(name = "LastModifiedbyId")
    private int LastModifiedbyId;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public LocalDateTime getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
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

    public void setCreateById(int createById) {
        CreateById = createById;
    }

    public int getLastModifiedbyId() {
        return LastModifiedbyId;
    }

    public void setLastModifiedbyId(int lastModifiedbyId) {
        LastModifiedbyId = lastModifiedbyId;
    }

}
