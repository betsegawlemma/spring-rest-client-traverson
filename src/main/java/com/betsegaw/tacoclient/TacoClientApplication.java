package com.betsegaw.tacoclient;

import java.net.URI;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;

import com.betsegaw.tacoclient.domains.Ingredient;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class TacoClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacoClientApplication.class, args);
	}
	
	@Bean
	public Traverson traverson() {
		return new Traverson(URI.create("http://localhost:8080/api"),MediaTypes.HAL_JSON);
	}
 
	 @Bean
	  public CommandLineRunner traversonGetIngredients(TacoRestClient tacoRestClient) {
	    return args -> {
	      Iterable<Ingredient> ingredients = tacoRestClient.getAllIngredientsWithTraverson();
	      log.info("----------------------- GET INGREDIENTS WITH TRAVERSON -------------------------");
	      for (Ingredient ingredient : ingredients) {
	        log.info("   -  " + ingredient);
	      }
	    };
	  }
}

