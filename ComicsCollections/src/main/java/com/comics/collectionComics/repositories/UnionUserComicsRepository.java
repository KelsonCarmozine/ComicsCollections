package com.comics.collectionComics.repositories;

import com.comics.collectionComics.models.unions.UnionUserCollectionComics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UnionUserComicsRepository extends JpaRepository<UnionUserCollectionComics, Long> {

    List<UnionUserCollectionComics> findAllByUserId(Long userId);
}
