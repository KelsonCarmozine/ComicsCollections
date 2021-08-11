package com.collections.comics.models.responses;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {
    private final HttpStatus status;
    private final List<String> message;


    public ErrorResponse(HttpStatus status, List<String> message) {
        this.status = status;
        this.message = message;
    }

    public ErrorResponse(HttpStatus status, String message) {
        this.status = status;
        List<String> listMessage = new ArrayList<>();
        listMessage.add(message);
        this.message = listMessage;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public List<String> getMessage() {
        return message;
    }
}
