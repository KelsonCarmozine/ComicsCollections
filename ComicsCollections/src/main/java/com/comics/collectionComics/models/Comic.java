package com.comics.collectionComics.models;

import com.comics.collectionComics.models.responses.ComicResponse;
import com.comics.collectionComics.models.unions.UnionComicPrices;
import com.comics.collectionComics.models.unions.UnionUserCollectionComics;
import com.comics.collectionComics.repositories.MarvelCreatorRepository;
import com.comics.collectionComics.repositories.MarvelPricesRepository;
import com.comics.collectionComics.repositories.UnionComicPricesRepository;
import com.comics.collectionComics.repositories.UnionCreatorsCreatorRepository;
import com.google.gson.annotations.SerializedName;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Entity
public class Comic{

    @Id
    @SerializedName("id")
    private Long comicId;
    private String title;
    @Transient
    private List<MarvelPrices> prices;
    @Transient
    private MarvelCreators creators;
    private Long creatorsId;
    private String isbn;
    private String description;
    private Integer rebateDay;

    public void preSet(Long userId, JpaRepository[] repositories) {
        this.creators.preSet(repositories);
        this.creatorsId = this.creators.getCreatorsId();
        createUnionComicPrices(repositories);
        createUnionUserComic(userId, repositories);
        setRebateDay();
        repositories[0].save(this);
    }

    private void createUnionComicPrices(JpaRepository[] repositories){
        this.prices.forEach(price -> {
            Long priceId = price.getId(repositories);
            new UnionComicPrices(this.comicId, priceId, repositories);;
        });
    }

    private void createUnionUserComic(Long userId, JpaRepository[] repositories) {
        new UnionUserCollectionComics(userId, this.comicId, repositories);
    }

    public ComicResponse covertToComicResponse(UnionComicPricesRepository unionComicPricesRepository,
                                               MarvelPricesRepository marvelPricesRepository,
                                               UnionCreatorsCreatorRepository unionCreatorsCreatorRepository,
                                               MarvelCreatorRepository marvelCreatorRepository) {
        return new ComicResponse(this,
                unionComicPricesRepository,
                marvelPricesRepository,
                unionCreatorsCreatorRepository,
                marvelCreatorRepository);
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

    public Long getCreatorsId() {
        return creatorsId;
    }

    public void setCreatorsId(Long creatorsId) {
        this.creatorsId = creatorsId;
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

    public void setRebateDay() {
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
}
