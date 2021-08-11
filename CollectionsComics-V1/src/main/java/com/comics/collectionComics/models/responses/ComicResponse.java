package com.comics.collectionComics.models.responses;

import com.comics.collectionComics.models.Comic;
import com.comics.collectionComics.models.MarvelCreator;
import com.comics.collectionComics.models.MarvelCreators;
import com.comics.collectionComics.models.MarvelPrices;
import com.comics.collectionComics.models.unions.UnionComicPrices;
import com.comics.collectionComics.models.unions.UnionCreatorsCreator;
import com.comics.collectionComics.repositories.MarvelCreatorRepository;
import com.comics.collectionComics.repositories.MarvelPricesRepository;
import com.comics.collectionComics.repositories.UnionComicPricesRepository;
import com.comics.collectionComics.repositories.UnionCreatorsCreatorRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;


public class ComicResponse {

    private Long comicId;
    private String title;
    private Object[] prices;
    private Object[] creators;
    private String isbn;
    private String description;
    private Integer rebateDay;
    private Boolean discount;


    public ComicResponse() {
    }

    public ComicResponse(Comic comic,
                         UnionComicPricesRepository unionComicPricesRepository,
                         MarvelPricesRepository marvelPricesRepository,
                         UnionCreatorsCreatorRepository unionCreatorsCreatorRepository,
                         MarvelCreatorRepository marvelCreatorRepository) {
        this.comicId = comic.getComicId();
        this.title = comic.getTitle();
        this.isbn = comic.getIsbn();
        this.description = comic.getDescription();
        this.rebateDay = comic.getRebateDay();
        this.discount = checkDiscount();

        List<UnionComicPrices> pricesId = unionComicPricesRepository.findAllByComicId(this.comicId);
        this.prices = pricesId.stream().map(unionComicPrice ->{
            MarvelPrices price = marvelPricesRepository.
                    findById(unionComicPrice.getPriceId())
                    .orElse(new MarvelPrices());
            if(this.discount){
                price.applyDiscount();
            }
            return price;
        }).toArray();


        List<UnionCreatorsCreator> creatorIds = unionCreatorsCreatorRepository
                .findAllByCreatorsId(comic.getCreatorsId())
                .orElse(new ArrayList<>());

          this.creators = creatorIds.stream().map(unionCreatorsCreator ->{
            MarvelCreator marvelCreator =  marvelCreatorRepository
                    .findById(unionCreatorsCreator.getCreatorId())
                    .orElse(new MarvelCreator());
            return marvelCreator;
        }).toArray();
    }



    private Boolean checkDiscount(){
        if(Objects.isNull(this.rebateDay)){
            return false;
        }
        Calendar myDate = Calendar.getInstance();
        Integer today = myDate.get(Calendar.DAY_OF_WEEK);
        return this.rebateDay.equals(today);
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

    public Object[] getPrices() {
        return prices;
    }

    public void setPrices(MarvelPrices[] prices) {
        this.prices = prices;
    }

    public Object[] getCreators() {
        return creators;
    }

    public void setCreators(MarvelCreators[] creators) {
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
