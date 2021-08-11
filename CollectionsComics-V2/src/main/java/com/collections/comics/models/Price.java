package com.collections.comics.models;

import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "prices")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long pricesId;
    private String type;
    private BigDecimal price;


    public void updatePrice(){
        BigDecimal percent = new BigDecimal("0.90");
        this.price = this.price.multiply(percent);
    }

    public String getType() {
        return type;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
