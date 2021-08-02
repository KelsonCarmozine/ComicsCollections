package com.comics.collectionComics.models.unions;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UnionCreatorsCreator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long unionCreatorsCreatorId;
    private Long creatorsId;
    private Long creatorId;


    public UnionCreatorsCreator() {
    }

    public UnionCreatorsCreator(Long creatorsId, Long creatorId, JpaRepository[] repositories) {
        this.creatorsId = creatorsId;
        this.creatorId = creatorId;
        repositories[4].save(this);
    }

    public Long getCreatorsId() {
        return creatorsId;
    }

    public void setCreatorsId(Long creatorsId) {
        this.creatorsId = creatorsId;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }
}
