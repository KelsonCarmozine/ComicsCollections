package com.comics.collectionComics.repositories;

import com.comics.collectionComics.models.unions.UnionComicPrices;
import com.comics.collectionComics.models.unions.UnionUserCollectionComics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UnionComicPricesRepository extends JpaRepository<UnionComicPrices, Long> {
    List<UnionComicPrices> findAllByComicId(Long comicId);
}
