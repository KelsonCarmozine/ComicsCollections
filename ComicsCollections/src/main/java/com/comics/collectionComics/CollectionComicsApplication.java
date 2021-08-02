package com.comics.collectionComics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableFeignClients
@EnableSwagger2
public class CollectionComicsApplication {

	public static void main(String[] args) {
		System.out.println("tes");SpringApplication.run(CollectionComicsApplication.class, args);
	}

}
