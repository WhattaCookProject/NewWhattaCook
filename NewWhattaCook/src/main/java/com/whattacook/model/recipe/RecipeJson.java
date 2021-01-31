package com.whattacook.model.recipe;

import static com.whattacook.util.TitleCase.all;
import static com.whattacook.util.Valid.notNullOrEmpty;

import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.whattacook.model.ingredient.Ingredient;
import com.whattacook.model.ingredient.IngredientJson;

public class RecipeJson {
	
	private String id;
	private String title;
	private String instructions;
	private SortedSet<IngredientJson> ingredients;
	
	
	public RecipeJson() {
	}

	public RecipeJson(String id, String title, String instructions, SortedSet<IngredientJson> ingredients) {
		this.id = id;
		this.title = title;
		this.instructions = instructions;
		this.ingredients = ingredients;
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
		
		if (notNullOrEmpty(id))
			recipe.setId(id);
		
		recipe.setTitle(all(title));
		recipe.setInstructions(instructions);
		recipe.setIngredients(getIngredients()
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
	
	public SortedSet<IngredientJson> getIngredients() {
		return ingredients;
	}

	public void setIngredients(SortedSet<IngredientJson> ingredients) {
		this.ingredients = ingredients;
	}

	@Override
	public String toString() {
		return String.format("Recipe : {id : %s, title : %s, instructions : %s, ingredientIds : %s}", 
				id, title, instructions, ingredients);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, title, instructions, ingredients);
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
		return Objects.equals(id, other.id) && Objects.equals(title, other.title)
				 && Objects.equals(instructions, other.instructions)
				 && Objects.equals(ingredients, other.ingredients);
	}

	
}
