package com.comics.collectionComics.repositories;

import com.comics.collectionComics.models.unions.UnionCreatorsCreator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UnionCreatorsCreatorRepository extends JpaRepository<UnionCreatorsCreator, Long> {
    Optional<List<UnionCreatorsCreator>> findAllByCreatorsId(Long creatorsId);
}
