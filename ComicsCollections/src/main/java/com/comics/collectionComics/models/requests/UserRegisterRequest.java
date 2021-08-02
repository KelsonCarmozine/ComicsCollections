package com.comics.collectionComics.models.requests;

import com.comics.collectionComics.models.UserCollection;
import com.comics.collectionComics.models.Abstracts.UserAbstract;


public class UserRegisterRequest extends UserAbstract {

    public UserCollection convertToUser(){
        return new UserCollection(this.name, this.email, this.cpf, this.birthday);
    }
}
