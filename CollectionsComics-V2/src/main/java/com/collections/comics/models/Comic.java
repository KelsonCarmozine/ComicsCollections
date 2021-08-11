package com.collections.comics.models;

import com.collections.comics.Creators;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "comics")
public class Comic {

    @Id
    @Column(name = "id")
    @SerializedName("id")
    protected Long comicId;
    protected String title;
    @OneToMany
    protected List<Price> prices;
    @Transient
    @JsonIgnore
    protected Creators creators;
    @ManyToMany
    protected List<Creator> creator;
    protected String isbn;
    protected String description;
    protected Integer rebateDay;

    private void discoverAndSerRebateDay(){
        Integer length = this.isbn.length();
        if (length.equals(0)){
            return;
        }
        char finish = this.isbn.charAt(length - 1);
        switch (finish) {
            case '0':
            case '1':
                this.rebateDay = Calendar.MONDAY;
                break;
            case '2':
            case '3':
                this.rebateDay = Calendar.TUESDAY;
                break;
            case '4':
            case '5':
                this.rebateDay = Calendar.WEDNESDAY;
                break;
            case '6':
            case '7':
                this.rebateDay = Calendar.THURSDAY;
                break;
            case '8':
            case '9':
                this.rebateDay = Calendar.FRIDAY;
                break;
        }
    }

    public Long getComicId() {
        return comicId;
    }

    public void setComicId(Long comicId) {
        this.comicId = comicId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    public List<Creator> findCreatorsFromCreator() {
        return this.creators.getListCreator();
    }

    public List<Creator> getCreator() {
        return this.creator;
    }

    public void setCreator(List<Creator> creator) {
        this.creator = creator;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
        discoverAndSerRebateDay();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRebateDay() {
        return rebateDay;
    }

    public Creators getCreators() {
        return creators;
    }

    public void setCreators(Creators creators) {
        this.creators = creators;
    }

//    public void setRebateDay(Integer rebateDay) {
//        this.rebateDay = rebateDay;
//
//
//    }
}
