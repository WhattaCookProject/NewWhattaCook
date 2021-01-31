package com.whattacook.model.recipe;

import static com.whattacook.model.KitchenFactory.emptyRecipe;
import static com.whattacook.model.KitchenFactory.emptyRecipeJson;
import static com.whattacook.model.KitchenFactory.fiveIngredientsJsonOnSortedSet;
import static com.whattacook.model.KitchenFactory.fiveIngredientsOnSortedSet;
import static com.whattacook.model.KitchenFactory.fullyRecipe;
import static com.whattacook.model.KitchenFactory.fullyRecipeJson;
import static com.whattacook.model.KitchenFactory.getId;
import static com.whattacook.model.KitchenFactory.getInstructions;
import static com.whattacook.model.KitchenFactory.getTitle;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.SortedSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.whattacook.model.ingredient.Ingredient;
import com.whattacook.model.ingredient.IngredientJson;

class RecipeTest {

	private final Recipe recipe = fullyRecipe();
	private final String id = getId();
	private final String title = getTitle();
	private final String instructions = getInstructions();
	private final SortedSet<Ingredient> ingredients = fiveIngredientsOnSortedSet();
	private final SortedSet<IngredientJson> ingredientsJson = fiveIngredientsJsonOnSortedSet();
	private final RecipeJson recipeJson = fullyRecipeJson();
	private Recipe toCompare;
	private RecipeJson toCompareJson;

	@BeforeEach
	void setUp() throws Exception {
		toCompare = null;
		toCompareJson = null;
	}

	@Test
	@DisplayName("Getters and Setters Recipe")
	void gettersSettersRecipeTest() {
		toCompare = new Recipe(id, title, instructions, ingredients);
		Recipe toCompare2 = emptyRecipe();
		toCompare2.setId(id);
		toCompare2.setTitle(title);
		toCompare2.setInstructions(instructions);
		toCompare2.setIngredients(ingredients);

		assertAll(() -> assertEquals(id, toCompare.getId(), "Equals 1"),
				() -> assertEquals(title, toCompare.getTitle(), "Equals 2"),
				() -> assertEquals(instructions, toCompare.getInstructions(), "Equals 3"),
				() -> assertEquals(ingredients, toCompare.getIngredients(), "Equals 4"),
				() -> assertEquals(recipe, toCompare, "Equals 5"), 
				() -> assertEquals(recipe, toCompare2, "Equals 6")
				);
	}

	@Test
	@DisplayName("Getters and Setters RecipeJson")
	void gettersSettersRecipeJsonTest() {
		toCompareJson = new RecipeJson(id, title, instructions, ingredientsJson);
		RecipeJson toCompareJson2 = emptyRecipeJson();
		toCompareJson2.setId(id);
		toCompareJson2.setTitle(title);
		toCompareJson2.setInstructions(instructions);
		toCompareJson2.setIngredients(ingredientsJson);

		assertAll(() -> assertEquals(id, toCompareJson.getId(), "Equals 1"),
				() -> assertEquals(title, toCompareJson.getTitle(), "Equals 2"),
				() -> assertEquals(instructions, toCompareJson.getInstructions(), "Equals 3"),
				() -> assertEquals(ingredientsJson, toCompareJson.getIngredients(), "Equals 4"),
				() -> assertEquals(recipeJson, toCompareJson, "Equals 5"),
				() -> assertEquals(recipeJson, toCompareJson2, "Equals 6")
				);
	}

	@Test
	@DisplayName("Recipe to Json")
	void teste_RecipeToJson() {
		toCompareJson = recipe.toJson();
		assertEquals(recipeJson, toCompareJson, "Equals");
	}

	@Test
	@DisplayName("Json to Recipe")
	void teste_JsonToRecipe() {
		toCompare = recipeJson.toRecipe();
		assertEquals(recipe, toCompare, "Equals 5");
	}

}
