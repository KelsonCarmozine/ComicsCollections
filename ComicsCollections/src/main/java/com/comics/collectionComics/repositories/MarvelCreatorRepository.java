package com.comics.collectionComics.repositories;

import com.comics.collectionComics.models.MarvelCreator;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MarvelCreatorRepository extends JpaRepository<MarvelCreator, Long> {
}
