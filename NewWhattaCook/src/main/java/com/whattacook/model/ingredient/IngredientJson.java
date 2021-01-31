package com.whattacook.model.ingredient;

import static com.whattacook.util.TitleCase.all;
import static com.whattacook.util.Valid.notNullOrEmpty;

import java.util.Objects;

public class IngredientJson implements Comparable<IngredientJson> {

	private String id;
	private String name;

	public IngredientJson() {
	}

	public IngredientJson(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public static IngredientJson from(Ingredient ingredient) {
		return new IngredientJson(ingredient.getId(), ingredient.getName());
	}

	public Ingredient toIngredient() {

		Ingredient ingredient = new Ingredient();

		ingredient.setName(all(name));

		if (notNullOrEmpty(id))
			ingredient.setId(id);

		return ingredient;
	}
	
	public static IngredientJson ERROR(String msg) {
		return new IngredientJson("ERROR", msg);
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
		return String.format("IngredientJson : {id : %s, name : %s}", id, name);
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
		IngredientJson other = (IngredientJson) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}

	@Override
	public int compareTo(IngredientJson o) {
		return this.getName().compareToIgnoreCase(o.getName());
	}


}
