package com.whattacook.model.ingredient;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class IngredientJsonTest {
	
	private String id;
	private String name;
	
	private IngredientJson ingredientJson;
	private IngredientJson ingredientJson2;
	

	@BeforeEach
	void setUp() throws Exception {
		id = "qwerty123456";
		name = "tomato";
		ingredientJson = new IngredientJson(id, name);
		ingredientJson2 = new IngredientJson();
		ingredientJson2.setId(id);
		ingredientJson2.setName(name);
		
	}

	@Test
	@DisplayName("Getters and Setters TESTING")
	void getteresAndSettersTest() {
		IngredientJson toCompare = new IngredientJson("qwerty123456", "tomato");
		
		assertAll(
				() -> assertEquals(id, toCompare.getId(), "equals1"),
				() -> assertEquals(name, toCompare.getName(), "equals2"),
				() -> assertEquals(ingredientJson, toCompare, "equals3"),
				() -> assertEquals(ingredientJson2, toCompare, "equals3")
				);
		
	}

}
