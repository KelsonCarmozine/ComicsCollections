package com.comics.collectionComics.models.responses;

public class ComicCreateResponse {

    private Long userId;
    private Long comicId;
    private String message = "Comic successfully created on user!";


    public ComicCreateResponse(Long userId, Long comicId) {

        this.userId = userId;
        this.comicId =comicId;
    }
}
