package com.whattacook.model.ingredient;

import java.util.Objects;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.whattacook.model.recipe.Recipe;

@Document(collection = "ingredient")
public class Ingredient {

	@Id
	private String id;
	private String name;
	@DBRef
	private Set<Recipe> recipeIds;
	
	public Ingredient() {}

	public Ingredient(String id, String name, Set<Recipe> recipeIds) {
		this.id = id;
		this.name = name;
		this.recipeIds = recipeIds;
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

	public Set<Recipe> getRecipeIds() {
		return recipeIds;
	}

	public void setRecipeIds(Set<Recipe> recipeIds) {
		this.recipeIds = recipeIds;
	}

	@Override
	public String toString() {
		return String.format("Ingredient : {id : %s, name : %s, recipeIds : %s}", id, name, recipeIds);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
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
		Ingredient other = (Ingredient) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}

}
