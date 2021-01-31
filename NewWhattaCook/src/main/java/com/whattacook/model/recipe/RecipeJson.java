package com.whattacook.model.recipe;

import java.util.Objects;

public class RecipeJson {
	
	private String id;
	private String title;
	private String instructions;
	
	
	public RecipeJson() {
	}

	public RecipeJson(String id, String title, String instructions) {
		this.id = id;
		this.title = title;
		this.instructions = instructions;
	}
	
	public RecipeJson(Recipe recipe) {
		this.id = recipe.getId();
		this.title = recipe.getTitle();
		this.instructions = recipe.getInstructions();
		this.ingredients = recipe.getIngredients()
				.stream().map(Ingredient::toJson)
				.collect(Collectors.toCollection(() -> new TreeSet<>()));
	}

	public static RecipeJson from(Recipe recipe) {
		return new RecipeJson(recipe);
	}
	
	public Recipe toRecipe() {
		Recipe recipe = new Recipe();
		
		if (notNullOrEmpty(this.id))
			recipe.setId(this.id);
		
		recipe.setTitle(this.title);
		recipe.setInstructions(this.instructions);
		recipe.setIngredients(this.getIngredients()
				.stream().map(IngredientJson::toIngredient)
				.collect(Collectors.toCollection(() -> new TreeSet<>())));
		
		return recipe;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	@Override
	public String toString() {
		return "RecipeJson [id=" + id + ", title=" + title + ", instructions=" + instructions + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, instructions, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		RecipeJson other = (RecipeJson) obj;
		return Objects.equals(id, other.id) && Objects.equals(instructions, other.instructions)
				&& Objects.equals(title, other.title);
	}

	
}
