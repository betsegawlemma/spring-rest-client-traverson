package com.betsegaw.tacoclient;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.betsegaw.tacoclient.domains.Ingredient;
import com.betsegaw.tacoclient.domains.Taco;

@Service
public class TacoRestClient {

	private RestTemplate restTemplate;

	public TacoRestClient(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	// Rest Template with HETOAES Disabled
	public Taco getTacoById(Long tacoId) {
		return restTemplate.getForObject("http://localhost:8080/design/{id}", Taco.class,tacoId);
	}
	
	public List<Taco> getRecentTacos(){
		return restTemplate.exchange("http://localhost:8080/design/recent",HttpMethod.GET,null,
				new ParameterizedTypeReference<List<Taco>>() {}).getBody();
	}

	public Ingredient getIngredientById(Long ingredientId) {
	    return restTemplate.getForObject("http://localhost:8080/ingredients/{id}",
	                             Ingredient.class, ingredientId);
	  }

	 public List<Ingredient> getAllIngredients() {
		    return restTemplate.exchange("http://localhost:8080/ingredients",
		            HttpMethod.GET, null, new ParameterizedTypeReference<List<Ingredient>>() {})
		        .getBody();
	 }
	
	 public void updateIngredient(Ingredient ingredient) {
		    restTemplate.put("http://localhost:8080/ingredients/{id}",
		          ingredient,ingredient.getId());
	}
	
	 public Ingredient createIngredient(Ingredient ingredient) {
		    return restTemplate.postForObject("http://localhost:8080/ingredients",
		        ingredient, Ingredient.class);
	}
	
	public void deleteIngredient(Ingredient ingredient) {
		restTemplate.delete("http://localhost:8080/ingredients/{id}",ingredient.getId());
	}

}
