package com.comics.collectionComics.models;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;

@Entity
public class UserCollection{

    @Id
    private Long userId;
    private String name;
    @Email
    @Column(unique=true)
    private String email;
    @CPF
    @Column(unique=true)
    private String cpf;
    private Date birthday;
    private Long unionUserComicsId;


    public UserCollection() {
    }

    public UserCollection(String name, String email, String cpf, Date birthday) {
        this.userId = createHash();
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.birthday = birthday;
    }

    private Long createHash(){
        String key = this.email+this.cpf;
        return (long) key.hashCode();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Long getUnionUserComicsId() { return unionUserComicsId; }

    public void setUnionUserComicsId(Long unionUserComicsId) { this.unionUserComicsId = unionUserComicsId; }
}
