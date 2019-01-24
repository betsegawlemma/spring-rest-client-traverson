package com.betsegaw.tacoclient.domains;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Taco {
	
	Long id;
	private Date createdAt;
	private String name;
	private Set<Ingredient> ingredients = new HashSet<>();

	public Taco(Long id, Date createdAt, String name, Set<Ingredient> ingredients) {
		this.id = id;
		this.createdAt = createdAt;
		this.name = name;
		this.ingredients = ingredients;
	}
	
		

}
