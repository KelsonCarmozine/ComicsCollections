package com.collections.comics;

import com.collections.comics.models.Creator;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Creators {

    @SerializedName("items")
    private List<Creator> listCreator;

    public List<Creator> getListCreator() {
        return listCreator;
    }

    public void setListCreator(List<Creator> listCreator) {
        this.listCreator = listCreator;
    }
}
