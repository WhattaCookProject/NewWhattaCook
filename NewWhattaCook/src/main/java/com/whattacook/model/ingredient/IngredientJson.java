package com.whattacook.model.ingredient;

public class IngredientJson {
	
	private String id;
	private String name;
	
	
	public IngredientJson() {
	}

	public IngredientJson(String id, String name) {
		this.id = id;
		this.name = name;
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
