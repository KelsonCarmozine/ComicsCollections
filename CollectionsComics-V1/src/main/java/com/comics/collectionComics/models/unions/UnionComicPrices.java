package com.comics.collectionComics.models.unions;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UnionComicPrices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long unionComicPricesId;
    private Long comicId;
    private Long priceId;


    public UnionComicPrices() {
    }

    public UnionComicPrices(Long comicId, Long priceId, JpaRepository[] repositories) {
        this.comicId = comicId;
        this.priceId = priceId;
        repositories[6].save(this);
    }

    public Long getComicId() {
        return comicId;
    }

    public void setComicId(Long comicId) {
        this.comicId = comicId;
    }

    public Long getPriceId() {
        return priceId;
    }

    public void setPriceId(Long priceId) {
        this.priceId = priceId;
    }
}
