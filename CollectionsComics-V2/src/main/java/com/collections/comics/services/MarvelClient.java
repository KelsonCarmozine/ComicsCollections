package com.collections.comics.services;

import com.collections.comics.models.Comic;
import com.collections.comics.models.MarvelData;
import feign.Feign;
import feign.Logger;
import feign.Param;
import feign.RequestLine;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.micrometer.MicrometerCapability;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "marvel", url = "https://gateway.marvel.com/")
public interface MarvelClient {

    @RequestLine("GET v1/public/comics/{comicId}?ts=1&apikey={apiKey}&hash={marvelHash}")
    MarvelData getComic(@Param("comicId") Long comicId,
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
                .target(MarvelClient.class, "https://gateway.marvel.com/");
    }
}
