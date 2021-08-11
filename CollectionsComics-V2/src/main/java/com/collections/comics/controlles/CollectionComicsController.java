package com.collections.comics.controlles;

import com.collections.comics.models.Comic;
import com.collections.comics.models.User;
import com.collections.comics.models.exception.EmailOrCpfAlreadyRegistered;
import com.collections.comics.models.exception.IncorrectUserIdException;
import com.collections.comics.models.requests.AddComicRequest;
import com.collections.comics.models.responses.AddComicResponse;
import com.collections.comics.models.responses.CreateUserResponse;
import com.collections.comics.models.responses.ErrorResponse;
import com.collections.comics.models.responses.ListComicsResponse;
import com.collections.comics.repositories.ComicsRepository;
import com.collections.comics.repositories.CreatorsRepository;
import com.collections.comics.repositories.PricesRepository;
import com.collections.comics.repositories.UsersRepository;
import com.collections.comics.services.MarvelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/collectionComics")
public class CollectionComicsController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ComicsRepository comicsRepository;

    @Autowired
    private PricesRepository pricesRepository;

    @Autowired
    private CreatorsRepository creatorsRepository;

    @Value("${marvelApiKey}")
    private String marvelApiKey;

    @Value("${marvelHash}")
    private String marvelHash;

    @PostMapping(value = "user")
    @Transactional
    public ResponseEntity createUser(@RequestBody @Valid User newUser, Errors errors) {
        if (errors.hasErrors()) {
            List<String> e = errors.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
        try {
            if(usersRepository.existsUserByEmailOrCpf(newUser.getEmail(), newUser.getCpf())){
                throw new EmailOrCpfAlreadyRegistered();
            }
            usersRepository.save(newUser);
            CreateUserResponse createUserResponse = new CreateUserResponse(newUser.getUserId());
            return ResponseEntity.ok(createUserResponse);
        }catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PostMapping(value = "comic", produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity addComics(@RequestBody @Valid AddComicRequest comicsRegisterRequest, Errors errors) {
        Long userId =comicsRegisterRequest.getUserId();
        Long comicId = comicsRegisterRequest.getComicId();

        if (errors.hasErrors()) {
            List<String> e = errors.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
        try {
            final MarvelClient marvelClient = MarvelClient.connect();

            User user = usersRepository
                    .findById(userId)
                    .orElseThrow(IncorrectUserIdException::new);

            Comic comic = comicsRepository
                    .findById(comicId)
                    .orElse(marvelClient
                            .getComic(
                                    comicId,
                                    marvelApiKey,
                                    marvelHash)
                            .getComic());

            pricesRepository.saveAll(comic.getPrices());
            comic.setCreator(comic.findCreatorsFromCreator());
            creatorsRepository.saveAll(comic.getCreator());
            comicsRepository.save(comic);
            user.add(comic);
            AddComicResponse addComicResponse = new AddComicResponse(userId, comicId);

            return ResponseEntity.ok(addComicResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping(value = "/listComics", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity listComics(@RequestParam Long userId) {

        try {
            usersRepository
                    .findById(userId)
                    .orElseThrow(IncorrectUserIdException::new);

            List<Comic> comics = usersRepository.findComicsByUserId(userId);

            ListComicsResponse listComicsResponse = new ListComicsResponse(userId, comics);

            return ResponseEntity.ok(listComicsResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
}
