package com.comics.collectionComics.models;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class MarvelPrices {

    @Id
    private Long pricesId;
    private String type;
    @Transient
    private BigDecimal price;
    private String stringPrice;


    public void applyDiscount(){
        BigDecimal percent = new BigDecimal("0.90");
        this.price = new BigDecimal(stringPrice);
        this.price = this.price.multiply(percent);
    }

    public Long getId(JpaRepository[] repositories) {
        this.pricesId = createHash();
        this.stringPrice = price.toString();
        repositories[5].save(this);
        return pricesId;
    }

    private Long createHash(){
        String key = this.type+this.price.toString();
        return (long) key.hashCode();
    }

    public Long getPricesId() { return pricesId; }

    public void setPricesId(Long pricesId) { this.pricesId = pricesId; }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
