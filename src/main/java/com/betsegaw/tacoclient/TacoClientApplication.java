package com.betsegaw.tacoclient;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.betsegaw.tacoclient.domains.Ingredient;
import com.betsegaw.tacoclient.domains.Taco;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class TacoClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacoClientApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public CommandLineRunner fetchTacos(TacoRestClient tacoRestClient) {
		return args -> {
			log.info("----------------GET----------------");
			log.info("Getting Tacos By Id");
			log.info("Tacos: "+tacoRestClient.getTacoById(4L));
			log.info("Getting Recent Tacos");
			Iterable<Taco> tacos = tacoRestClient.getRecentTacos();
			log.info("Recent taco designs: ");
			for(Taco taco: tacos) {
				log.info("  -" + taco);
			}
		};
	}
	
	@Bean
	public CommandLineRunner fetchIngredients(TacoRestClient tacoRestClient) {
		return args -> {
			log.info("----------------GET----------------");
			log.info("Getting Ingredient By Id");
			log.info("Ingredient: "+tacoRestClient.getIngredientById(1L));
			log.info("Getting All Ingredients");
			Iterable<Ingredient> ingredients = tacoRestClient.getAllIngredients();
			log.info("All ingredients: ");
			for(Ingredient ingredient: ingredients) {
				log.info("  -" + ingredient);
			}
		};
	}
	
	
	 @Bean
	  public CommandLineRunner putAnIngredient(TacoRestClient tacoRestClient) {
	    return args -> {
	      log.info("----------------------- PUT -------------------------");
	      Ingredient before = tacoRestClient.getIngredientById(1L);
	      log.info("BEFORE:  " + before);
	      tacoRestClient.updateIngredient(new Ingredient(1L, "Flour Tortillas", Ingredient.Type.WRAP));
	      Ingredient after = tacoRestClient.getIngredientById(1L);
	      log.info("AFTER:  " + after);
	    };
	  }
	
	 @Bean
	  public CommandLineRunner addAnIngredient(TacoRestClient tacoRestClient) {
	    return args -> {
	      log.info("----------------------- POST -------------------------");
	      Ingredient chix = new Ingredient(0L, "Shredded Chicken", Ingredient.Type.PROTEIN);
	      Ingredient chixAfter = tacoRestClient.createIngredient(chix);
	      log.info("AFTER=1:  " + chixAfter);
	      Ingredient beefFajita = new Ingredient(0L, "Beef Fajita", Ingredient.Type.PROTEIN);
	      tacoRestClient.createIngredient(beefFajita);
	      Ingredient shrimp = new Ingredient(0L, "Shrimp", Ingredient.Type.PROTEIN);
	      tacoRestClient.createIngredient(shrimp);
	    };
	  }
	 
	 @Bean
	  public CommandLineRunner deleteAnIngredient(TacoRestClient tacoRestClient) {
	    return args -> {
	      log.info("----------------------- DELETE -------------------------");
	      // Please use existing id in the database to see if the delete work
	      tacoRestClient.deleteIngredient(tacoRestClient.getIngredientById(51L));
	      log.info("The ingredient has been deleted or does not exist");
	     
	    };
	  }
}

