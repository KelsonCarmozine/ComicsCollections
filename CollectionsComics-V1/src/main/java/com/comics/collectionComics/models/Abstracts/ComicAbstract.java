package com.comics.collectionComics.models.Abstracts;

import com.comics.collectionComics.models.MarvelCreators;
import com.comics.collectionComics.models.MarvelPrices;

import java.util.Calendar;
import java.util.List;


public abstract class ComicAbstract {

    protected Long comicId;
    protected String title;
    protected List<MarvelPrices> prices;
    protected MarvelCreators creators;
    protected String isbn;
    protected String description;
    protected Integer rebateDay;


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

    public List<MarvelPrices> getPrices() {
        return prices;
    }

    public void setPrices(List<MarvelPrices> prices) {
        this.prices = prices;
    }

    public MarvelCreators getCreators() {
        return creators;
    }

    public void setCreators(MarvelCreators creators) {
        this.creators = creators;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
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

    public void setRebateDay(Integer rebateDay) {
        this.rebateDay = rebateDay;
    }

    public void setRebateDay() {
        char finish = this.isbn.charAt(this.isbn.length() - 1);
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
}
