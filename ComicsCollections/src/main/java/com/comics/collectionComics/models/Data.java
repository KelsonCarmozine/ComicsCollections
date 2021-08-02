package com.comics.collectionComics.models;

import com.google.gson.annotations.SerializedName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;


public class Data {

    @SerializedName("data")
    public MarvelResponse marvelResponse;


    public class MarvelResponse {

        @SerializedName("results")
        private List<Comic> comics;


        private void preSet(Long userId, JpaRepository[] repositories){
            this.comics.forEach(comic -> {comic.preSet(userId, repositories);});
        }

        public Comic getResults(Long userId, JpaRepository[] repositories) {
            preSet(userId, repositories);
            return comics.get(0);
        }
        public void setResults(Comic[] results) {
            this.comics = Arrays.asList(results);
        }
    }
}
