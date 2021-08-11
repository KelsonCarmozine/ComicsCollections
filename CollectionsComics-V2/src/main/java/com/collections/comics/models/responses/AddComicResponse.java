package com.collections.comics.models.responses;

public class AddComicResponse {

    private Long userId;
    private Long comicId;
    private final String message = "Comic successfully created on user!";


    public AddComicResponse(Long userId, Long comicId) {

        this.userId = userId;
        this.comicId =comicId;
    }


    public Long getUserId() {
        return userId;
    }

    public Long getComicId() {
        return comicId;
    }

    public String getMessage() {
        return message;
    }
}
