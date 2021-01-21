package com.whattacook.model.ingredient;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ingredient")
public class Ingredient {

	@Id
	private String id;
	private String name;
	
	public Ingredient() {}

	public Ingredient(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	
	public IngredientJson toJson() {
		return IngredientJson.from(this);
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

	@Override
	public String toString() {
		return String.format("Ingredient : {id : %s, name : %s}", id, name);
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
