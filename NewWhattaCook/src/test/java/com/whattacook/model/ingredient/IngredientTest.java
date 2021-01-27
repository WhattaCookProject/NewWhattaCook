package com.whattacook.model.ingredient;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class IngredientTest {
	
	private String id;
	private String name;
	
	private Ingredient ingredient;
	private Ingredient ingredient2;
	
	private IngredientJson ingredientJson;
	private IngredientJson ingredientJson2;


	@BeforeEach
	void setUp() throws Exception {
		id = "ascdvfbgnhjm2345678";
		name = "potato";
		
		ingredient = new Ingredient(id, name);
		ingredient2 = new Ingredient();
		ingredient2.setId(id);
		ingredient2.setName(name);
		
		ingredientJson = new IngredientJson(id, name);
		ingredientJson2 = new IngredientJson();
		ingredientJson2.setId(id);
		ingredientJson2.setName(name);
	}

	@Test
	@DisplayName("Getters&Setters Ingredient")
	void gettersSettersIngredientTest() {
		Ingredient toCompare = new Ingredient("ascdvfbgnhjm2345678", "potato");
		
		assertAll(
				() -> assertEquals(id, toCompare.getId(), "equals1"),
				() -> assertEquals(name, toCompare.getName(), "equals2"),
				() -> assertEquals(ingredient, toCompare, "equals3"),
				() -> assertEquals(ingredient2, toCompare, "equals4")
				);
	}
	
	@Test
	@DisplayName("Getters and Setters IngredientJson")
	void getteresSettersIngredientJsonTest() {
		IngredientJson toCompare = new IngredientJson("ascdvfbgnhjm2345678", "potato");
		
		assertAll(
				() -> assertEquals(id, toCompare.getId(), "equals1"),
				() -> assertEquals(name, toCompare.getName(), "equals2"),
				() -> assertEquals(ingredientJson, toCompare, "equals3"),
				() -> assertEquals(ingredientJson2, toCompare, "equals3")
				);
	}

}
