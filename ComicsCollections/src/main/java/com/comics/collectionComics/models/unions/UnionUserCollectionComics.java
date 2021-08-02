package com.comics.collectionComics.models.unions;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UnionUserCollectionComics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long unionUserComics;
    private Long userId;
    private Long comicId;


    public UnionUserCollectionComics() {
    }

    public UnionUserCollectionComics(Long userId, Long comicId, JpaRepository[] repositories) {
        this.userId = userId;
        this.comicId = comicId;
        repositories[1].save(this);
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getComicId() {
        return comicId;
    }

    public void setComicId(Long comicId) {
        this.comicId = comicId;
    }
}
