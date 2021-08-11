package com.collections.comics.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.function.Supplier;


public class MarvelData {

    @SerializedName("data")
    public MarvelResponse marvelResponse;

    public Comic getComic() {
        return this.marvelResponse.comics.get(0);
    }


    public class MarvelResponse {

        @SerializedName("results")
        private List<Comic> comics;

    }
}
