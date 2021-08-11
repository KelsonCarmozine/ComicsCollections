package com.comics.collectionComics.controller;

import com.comics.collectionComics.models.Comic;
import com.comics.collectionComics.models.MarvelCreator;
import com.comics.collectionComics.models.UserCollection;
import com.comics.collectionComics.models.exception.IncorrectUserIdException;
import com.comics.collectionComics.models.requests.ComicRegisterRequest;
import com.comics.collectionComics.models.requests.UserRegisterRequest;
import com.comics.collectionComics.models.responses.ComicCreateResponse;
import com.comics.collectionComics.models.responses.ComicResponse;
import com.comics.collectionComics.models.responses.ListComicResponse;
import com.comics.collectionComics.models.responses.UserCreateResponse;
import com.comics.collectionComics.models.unions.UnionUserCollectionComics;
import com.comics.collectionComics.repositories.*;
import com.comics.collectionComics.services.MarvelClient;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/collectionComics")
public class CollectionComicsController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ComicsRepository comicsRepository;

    @Autowired
    private MarvelCreatorRepository marvelCreatorRepository;

    @Autowired
    private MarvelCreatorsRepository marvelCreatorsRepository;

    @Autowired
    private MarvelPricesRepository marvelPricesRepository;

    @Autowired
    private UnionUserComicsRepository unionUserComicsRepository;

    @Autowired
    private UnionComicPricesRepository unionComicPricesRepository;

    @Autowired
    private  UnionCreatorsCreatorRepository unionCreatorsCreatorRepository;

    private JpaRepository[] repositories = new JpaRepository[7];

    private Gson gson = new Gson();

    @Value("${marvelApiKey}")
    private String marvelApiKey;

    @Value("${marvelHash}")
    private String marvelHash;

    @PostMapping(value = "user", produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity createUser(@RequestBody @Valid UserRegisterRequest newUser) {
        try {
            UserCollection userCollection = newUser.convertToUser();
            usersRepository.save(userCollection);
            UserCreateResponse userCreateResponse = new UserCreateResponse(userCollection.getUserId());

//            String json = gson.toJson(userCreateResponse);
            return ResponseEntity.status(HttpStatus.CREATED).body(json);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(value = "comic", produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity addComics(@RequestBody @Valid ComicRegisterRequest comicsRegisterRequest) {
        Long userId =comicsRegisterRequest.getUserId();
        Long comicId = comicsRegisterRequest.getComicId();

        repositories[0] = comicsRepository;
        repositories[1] = unionUserComicsRepository;
        repositories[2] = marvelCreatorsRepository;
        repositories[3] = marvelCreatorRepository;
        repositories[4] = unionCreatorsCreatorRepository;
        repositories[5] = marvelPricesRepository;
        repositories[6] = unionComicPricesRepository;

        try {
            final MarvelClient marvelClient = MarvelClient.connect();

            UserCollection userCollection = usersRepository
                    .findById(userId)
                    .orElseThrow(IncorrectUserIdException::new);

            Comic newComic = comicsRepository
                    .findById(comicId)
                    .orElse(marvelClient.getComic(comicId,
                                                  marvelApiKey,
                                                  marvelHash).marvelResponse.getResults(userId, repositories));

            ComicCreateResponse comicCreateResponse = new ComicCreateResponse(userId, comicId);

            String json = gson.toJson(comicCreateResponse);
            return ResponseEntity.status(HttpStatus.CREATED).body(json);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value = "/listComics", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity listComics(@RequestParam Long userId) {

        try {
            usersRepository
                    .findById(userId)
                    .orElseThrow(IncorrectUserIdException::new);

            List<UnionUserCollectionComics> unionUserCollectionComics = unionUserComicsRepository.findAllByUserId(userId);
            List<ComicResponse> comicsResponseList = unionUserCollectionComics.stream().map(comicId -> {
                Comic comic = comicsRepository.findById(comicId.getComicId()).orElse(new Comic());
                return    comic.covertToComicResponse(unionComicPricesRepository, marvelPricesRepository, unionCreatorsCreatorRepository, marvelCreatorRepository);
            }).collect(Collectors.toList());
            ListComicResponse listComicResponse = new ListComicResponse(userId, comicsResponseList);

            String json = gson.toJson(listComicResponse);
            return ResponseEntity.ok(json);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
