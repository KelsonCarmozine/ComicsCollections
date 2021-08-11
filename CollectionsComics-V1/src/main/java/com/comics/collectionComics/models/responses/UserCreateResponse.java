package com.comics.collectionComics.models.responses;

public class UserCreateResponse {

    private Long userId;
    private String message = "User successfully created!";


    public UserCreateResponse(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getMessage() {
        return message;
    }
}
