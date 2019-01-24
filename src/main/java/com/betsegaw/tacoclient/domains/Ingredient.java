package com.betsegaw.tacoclient.domains;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Ingredient
{
	private Long id;
	private String name;
	private Type type;
	
	public static enum Type {
		WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
	}

	public Ingredient(Long id, String name, Type type) {
		this.id = id;
		this.name = name;
		this.type = type;
	}
}
