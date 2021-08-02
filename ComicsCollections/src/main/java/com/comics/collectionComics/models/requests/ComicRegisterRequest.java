package com.comics.collectionComics.models.requests;

import javax.validation.constraints.NotNull;


public class ComicRegisterRequest {

    @NotNull
    private Long userId;
    @NotNull
    private Long comicId;

    public Long getUserId() {
        return userId;
    }

    public Long getComicId() {
        return comicId;
    }
}
