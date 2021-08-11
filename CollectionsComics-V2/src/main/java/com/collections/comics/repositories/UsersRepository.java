package com.collections.comics.repositories;

import com.collections.comics.models.Comic;
import com.collections.comics.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface UsersRepository extends CrudRepository<User, Long> {

    Boolean existsUserByEmailOrCpf(String email, String cpf);

    @Query("SELECT u.comics FROM User u WHERE u.id = :userId")
    List<Comic> findComicsByUserId(Long userId);
}
