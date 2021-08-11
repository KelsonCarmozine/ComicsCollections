package com.collections.comics.models;

import javax.persistence.*;

@Entity
@Table(name = "creator")
public class Creator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long creatorId;
    private String resourceURI;
    private String name;
    private String role;

    public String getResourceURI() {
        return resourceURI;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
}
