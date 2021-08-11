package com.collections.comics.models.responses;

import com.collections.comics.models.Comic;

import java.util.List;

public class ListComicsResponse {

    private Long userId;
    private List<Comic> comics;


    public ListComicsResponse(Long userId, List<Comic> comics) {
        this.userId = userId;
        this.comics = comics;
    }

    public void setComics(List<Comic> comics) {
        this.comics = comics;
    }

    public Long getUserId() {
        return userId;
    }

    public List<Comic> getComics() {
        return comics;
    }
}
