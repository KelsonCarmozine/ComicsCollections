package com.comics.collectionComics.services;

import com.comics.collectionComics.models.Data;
import feign.*;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.micrometer.MicrometerCapability;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "marvel", url = "https://gateway.marvel.com/")
public interface MarvelClient {

    @RequestLine("GET v1/public/comics/{comicId}?ts=1&apikey={apiKey}&hash={marvelHash}")
    Data getComic(@Param("comicId") Long comicId,
                      @Param("apiKey") String apiKey,
                      @Param("marvelHash") String marvelHash);

    static MarvelClient connect() {
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(MarvelClient.class))
                .logLevel(Logger.Level.FULL)
                .addCapability(new MicrometerCapability())
//                .errorDecoder(new GitHubErrorDecoder(decoder)) // criar
                .target(MarvelClient.class, "https://gateway.marvel.com/");
    }
}
