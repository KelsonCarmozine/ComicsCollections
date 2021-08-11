package com.collections.comics.models;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "clients")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long userId;
    @NotNull
    private String name;
    @Email
    @Column(unique=true)
    private String email;
    @CPF
    @Column(unique=true)
    private String cpf;
    @ManyToMany
    private List<Comic> comics = new ArrayList<>();
    private LocalDate birthday;


    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Comic> getComics() {
        return comics;
    }

    public void setComics(List<Comic> comics) {
        this.comics = comics;
    }

    public void add(Comic comic) {
        this.comics.add(comic);
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
