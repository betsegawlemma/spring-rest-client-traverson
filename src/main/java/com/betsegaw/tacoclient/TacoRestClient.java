package com.betsegaw.tacoclient;

import java.util.Collection;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.client.Traverson;
import org.springframework.stereotype.Service;

import com.betsegaw.tacoclient.domains.Ingredient;

@Service
public class TacoRestClient {

	private Traverson traverson;

	public TacoRestClient(Traverson traverson) {
		this.traverson = traverson;
	}
	
	public Iterable<Ingredient> getAllIngredientsWithTraverson() {
		
		ParameterizedTypeReference<Resources<Ingredient>> ingredientType = 
			new ParameterizedTypeReference<Resources<Ingredient>>() {};
			
			Resources<Ingredient> ingredientResources = 
					traverson.follow("ingredients").toObject(ingredientType);
			Collection<Ingredient> ingredients = ingredientResources.getContent();
			
			return ingredients;
	}	

}
