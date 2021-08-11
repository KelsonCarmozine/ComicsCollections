package com.collections.comics.models.responses;

import com.collections.comics.models.Comic;
import com.collections.comics.models.Price;

import java.util.Calendar;
import java.util.Objects;

public class ComicResponse extends Comic {

    private Boolean discount;


    public ComicResponse(Comic comic) {
        this.comicId = comic.getComicId();
        this.title = comic.getTitle();
        this.isbn = comic.getIsbn();
        this.description = comic.getDescription();
        this.prices = comic.getPrices();
        this.rebateDay = comic.getRebateDay();
        setDiscount();
        updatePrices();
    }

    private void setDiscount(){
        if(Objects.isNull(this.rebateDay)){
            this.discount = false;
        }
        Calendar myDate = Calendar.getInstance();
        Integer today = myDate.get(Calendar.DAY_OF_WEEK);
        this.discount = this.rebateDay.equals(today);
    }

    private void updatePrices(){
        if(this.discount) {
            this.prices.forEach(Price::updatePrice);
        }
    }

    public Boolean getDiscount() {
        return discount;
    }
}
