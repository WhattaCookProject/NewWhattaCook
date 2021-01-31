package com.whattacook.model.ingredient;

import static com.whattacook.model.KitchenFactory.emptyIngredient;
import static com.whattacook.model.KitchenFactory.emptyIngredientJson;
import static com.whattacook.model.KitchenFactory.fullyIngredient;
import static com.whattacook.model.KitchenFactory.fullyIngredientJson;
import static com.whattacook.model.KitchenFactory.getId;
import static com.whattacook.model.KitchenFactory.getName;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class IngredientTest {

	private final String ID = getId();
	private final String NAME = getName();
	private final Ingredient ingredient = fullyIngredient();
	private final IngredientJson ingredientJson = fullyIngredientJson();
	private Ingredient toCompare;
	private IngredientJson toCompareJson;

	@BeforeEach
	void setUp() throws Exception {
		toCompare = null;
		toCompareJson = null;
	}

	@Test
	@DisplayName("Getters and Setters Ingredient")
	void gettersSettersIngredientTest() {
		toCompare = new Ingredient("ImpossibleInDataBase", "AbraCadaBra");
		Ingredient ingredient2 = emptyIngredient();
		ingredient2.setId(ID);
		ingredient2.setName(NAME);

		assertAll(
				() -> assertEquals(ID, toCompare.getId(), "Equals 1"),
				() -> assertEquals(NAME, toCompare.getName(), "Equals 2"),
				() -> assertEquals(ingredient, toCompare, "Equals 3"),
				() -> assertTrue(ingredient2.equals(toCompare), "True 1")
				);
	}

	@Test
	@DisplayName("Getters and Setters IngredientJson")
	void getteresSettersIngredientJsonTest() {
		toCompareJson = new IngredientJson("ImpossibleInDataBase", "AbraCadaBra");
		IngredientJson ingredientJson2 = emptyIngredientJson();
		ingredientJson2.setId(ID);
		ingredientJson2.setName(NAME);

		assertAll(
				() -> assertEquals(ID, toCompareJson.getId(), "Equals 1"),
				() -> assertEquals(NAME, toCompareJson.getName(), "Equals 2"),
				() -> assertEquals(ingredientJson, toCompareJson, "Equals 3"),
				() -> assertTrue(ingredientJson2.equals(toCompareJson), "True 1")
				);
	}

	@Test
	@DisplayName("Ingredient to Json")
	void test_IngredientToJson() {
		toCompareJson = ingredient.toJson();

		assertAll(
				() -> assertEquals(ingredientJson, toCompareJson, "Equals 1"),
				() -> assertTrue(ingredientJson.equals(toCompareJson), "True 1")
				);
	}

	@Test
	@DisplayName("Json to Ingredient")
	void test_JsonToIngredient() {
		toCompare = ingredientJson.toIngredient();

		assertAll(
				() -> assertEquals(ingredient, toCompare, "Equals 1"),
				() -> assertTrue(ingredient.equals(toCompare), "True 1")
				);
	}

}
