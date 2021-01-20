package com.whattacook.model.ingredient;


public class IngredientJson {
	
	private String id;
	private String name;
	
	
	public IngredientJson() {
	}

	public IngredientJson(Ingredient ingredient) {
		this.id = ingredient.getId();
		this.name = ingredient.getName();
	}

	
	public static IngredientJson from(Ingredient ingredient) {
		return new IngredientJson(ingredient);
	}

	public Ingredient toIngredient() {
		
		Ingredient ingredient = new Ingredient();
		
		ingredient.setId(id);
		ingredient.setName(name);
		return ingredient;
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
