package com.comics.collectionComics.models.responses;

import java.util.List;

public class ListComicResponse {

    private List<ComicResponse> comics;
    private Long userId;
    private String message;


    public ListComicResponse(Long userId, List<ComicResponse> comics) {

        this.userId = userId;
        this.comics = comics;
        this.message = "User comic List!";
    }
}
