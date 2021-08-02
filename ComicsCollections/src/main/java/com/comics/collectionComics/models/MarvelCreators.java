package com.comics.collectionComics.models;

import com.comics.collectionComics.models.unions.UnionCreatorsCreator;
import com.google.gson.annotations.SerializedName;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.*;
import java.util.List;

@Entity
public class MarvelCreators {

    @Id
    private Long creatorsId;
    private Integer available;
    private String collectionURI;
    @Transient
    @SerializedName("items")
    private List<MarvelCreator> listCreator;


    public void preSet(JpaRepository[] repositories){
        this.creatorsId = createHash();
        this.listCreator.forEach(creator -> {
            Long creatorId = creator.getId(repositories);
            createUnionCreatorsCreator(creatorId, repositories);
        });
        repositories[2].save(this);
    }

    private Long createHash(){
        String key = this.available.toString()+this.collectionURI;
        return (long) key.hashCode();
    }

    private void createUnionCreatorsCreator( Long creatorId, JpaRepository[] repositories){
            new UnionCreatorsCreator(this.creatorsId, creatorId, repositories);
    }

    public Long getCreatorsId() {
        return creatorsId;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public String getCollectionURI() {
        return collectionURI;
    }

    public void setCollectionURI(String collectionURI) {
        this.collectionURI = collectionURI;
    }

    public List<MarvelCreator> getListCreator() {
        return listCreator;
    }

    public void setListCreator(List<MarvelCreator> listCreator) {
        this.listCreator = listCreator;
    }
}
