package com.collections.comics.models.exception;


public class EmailOrCpfAlreadyRegistered extends Exception {
    public EmailOrCpfAlreadyRegistered() {
        super("Email or cpf informed are already registered!");
    }
}
