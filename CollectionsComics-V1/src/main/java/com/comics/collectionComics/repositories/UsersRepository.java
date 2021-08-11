package com.comics.collectionComics.repositories;

import com.comics.collectionComics.models.UserCollection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UsersRepository extends JpaRepository<UserCollection, Long> {
    Optional<List<Long>> findListComicsIdByUserId(Long userId);
}
