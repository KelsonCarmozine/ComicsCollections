package com.collections.comics.models.exception;


public class IncorrectUserIdException extends Exception {
    public IncorrectUserIdException() {
        super("There is no user with the allowed userId!");
    }
}
