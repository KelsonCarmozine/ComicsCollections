package com.comics.collectionComics.models;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Entity
public class MarvelCreator {

    @Id
    private Long creatorId;
    private String resourceURI;
    private String name;
    private String role;


    public Long getId(JpaRepository[] repositories){
        Long id = createHash();
        this.creatorId = id;
        repositories[3].save(this);
        return creatorId;
    }

    private Long createHash(){
        String key = this.resourceURI+this.name+this.role;
        return (long) key.hashCode();
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
