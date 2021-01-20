package com.whattacook.model.recipe;

import java.util.Objects;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.whattacook.model.ingredient.Ingredient;

@Document(collection = "recipe")
public class Recipe {

	@Id
	private String id;
	private String title;
	private String instructions;
	@DBRef
	private Set<Ingredient> ingredientIds;
	
	public Recipe() {}

	public Recipe(String id, String title, String instructions, Set<Ingredient> ingredientIds) {
		this.id = id;
		this.title = title;
		this.instructions = instructions;
		this.ingredientIds = ingredientIds;
	}

	public Recipe(String id, String title, String instructions) {
		this.id = id;
		this.title = title;
		this.instructions = instructions;
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

	public Set<Ingredient> getIngredientIds() {
		return ingredientIds;
	}

	public void setIngredientIds(Set<Ingredient> ingredientIds) {
		this.ingredientIds = ingredientIds;
	}

	@Override
	public String toString() {
		return String.format("Recipe : {id : %s, title : %s, instructions : %s, ingredientIds : %s}", 
				id, title, instructions, ingredientIds);
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
		Recipe other = (Recipe) obj;
		return Objects.equals(id, other.id) && Objects.equals(instructions, other.instructions)
				&& Objects.equals(title, other.title);
	}
	
}
