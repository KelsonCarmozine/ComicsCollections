package com.collections.comics.models.responses;

public class CreateUserResponse {

    private Long userId;
    private final String message = "User successfully created!";


    public CreateUserResponse(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getMessage() {
        return message;
    }
}
