package com.comics.collectionComics.repositories;

import com.comics.collectionComics.models.MarvelCreators;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MarvelCreatorsRepository extends JpaRepository<MarvelCreators, Long> {
}
