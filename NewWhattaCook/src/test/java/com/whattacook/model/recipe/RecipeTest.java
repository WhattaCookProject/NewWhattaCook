package com.whattacook.model.recipe;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class RecipeTest {
	private String id;
	private String title;
	private String instructions;
//	private Set<Ingredient> ingredientIds;
	private Recipe recipe;
	private Recipe recipe2;
	private RecipeJson recipeJson;
	private RecipeJson recipeJson2;
	
	
	@BeforeEach
	void setUp() throws Exception {
		id = "ascdvfbgnhjm2345678";
		title = "Lasagna";
		instructions = "This is how you do it";
		recipe = new Recipe(id, title, instructions);
		recipe2 = new Recipe();
		recipe2.setId(id);
		recipe2.setTitle(title);
		recipe2.setInstructions(instructions);
		recipeJson = new RecipeJson(id, title, instructions);
		recipeJson2 = new RecipeJson();
		recipeJson2.setId(id);
		recipeJson2.setTitle(title);
		recipeJson2.setInstructions(instructions);
	}

	@Test
	@DisplayName("Getters and Setters Recipe")
	void gettersSettersRecipeTest() {
		Recipe toCompare = new Recipe("ascdvfbgnhjm2345678", "Lasagna","This is how you do it");
		
		assertAll(
				() -> assertEquals(id, toCompare.getId(), "equals1"),
				() -> assertEquals(title, toCompare.getTitle(), "equals2"),
				() -> assertEquals(instructions, toCompare.getInstructions(), "equals3"),
				() -> assertEquals(recipe, toCompare, "equals4"),
				() -> assertEquals(recipe2, toCompare, "equals5")
				);
	}
	
	@Test
	@DisplayName("Getters and Setters RecipeJson")
	void gettersSettersRecipeJsonTest() {
		RecipeJson toCompare = new RecipeJson("ascdvfbgnhjm2345678", "Lasagna","This is how you do it");
		
		assertAll(
				() -> assertEquals(id, toCompare.getId(), "equals1"),
				() -> assertEquals(title, toCompare.getTitle(), "equals2"),
				() -> assertEquals(instructions, toCompare.getInstructions(), "equals3"),
				() -> assertEquals(recipeJson, toCompare, "equals4"),
				() -> assertEquals(recipeJson2, toCompare, "equals5")
				);
	}

}
