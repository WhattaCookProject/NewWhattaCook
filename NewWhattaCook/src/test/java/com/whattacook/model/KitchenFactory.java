package com.whattacook.model;

import java.util.SortedSet;
import java.util.TreeSet;

import com.whattacook.model.ingredient.Ingredient;
import com.whattacook.model.ingredient.IngredientJson;
import com.whattacook.model.recipe.Recipe;
import com.whattacook.model.recipe.RecipeJson;

public class KitchenFactory {

	private static Recipe recipe;
	private static RecipeJson recipeJson;
	private static Ingredient ingredient;
	private static IngredientJson ingredientJson;
	private static SortedSet<Ingredient> ingredientSet;
	private static SortedSet<IngredientJson> ingredientJsonSet;
	private final static String ID = "ImpossibleInDataBase";
	private final static String NAME = "AbraCadaBra";
	private final static String TITLE = "Hocus Pocus";
	private final static String INSTRUCTIONS = "1 - Prepara el molde. Yo he utilizado una flanera de 16 centímetros de diámetro por 10 de alto. Rocía el molde con spray desmoldante, o un poco de mantequilla, y extiéndelo bien por todo el molde.\n"
			+ "2 - Prepara el caramelo. Pon el azúcar en un cazo al fuego y añade el agua necesaria para mojar el azúcar. No hay que hacer nada, solo esperar a que se dore el azúcar. Vierte el caramelo al molde cuando adquiera el color dorado que te guste.\n"
			+ "3 - Introduce una bandeja con agua en el horno para hacer el baño maría con el horno precalentado a 190 grados con calor arriba y abajo.\n"
			+ "4 - Prepara el brownie. Derrite la mantequilla en el microondas. Trocea el chocolate y déjalo en un bol. Vierte la mantequilla en el bol del chocolate y mezcla hasta que se derrita el chocolate por completo. En otro bol, pon los 3 huevos, añade el azúcar y mezcla con una batidora. Añade el chocolate cuando los huevos hayas blanqueado y bate de nuevo. Añade la harina, la pizca de sal y el cacao en polvo. Mezcla hasta integrar todos los ingredientes. Vierte la mezcla al molde. Mueve un poco el molde para nivelar el brownie.\n"
			+ "5 - Prepara el flan. Pon los huevos en un bol e incorpora el azúcar. Bate e incorpora la leche. Sigue batiendo hasta mezclar bien todos los ingredientes. Vierte el flan en el molde a través de una cuchara vuelta del revés para que caiga de forma suave.\n"
			+ "6 - Cubre el molde con papel de aluminio y llévalo al horno. Coloca el molde en el horno dentro de la bandeja con agua. Hornea durante una hora u hora y cuarto a 180 o 190 grados.\n"
			+ "7 - Déjalo enfriar, primero a temperatura ambiente, y después en la nevera durante aproximadamente 3 horas.\n"
			+ "8 - Desmolda el pastel y ya lo tienes listo para servir y comer.";

	public static Ingredient emptyIngredient() {
		ingredient = new Ingredient();
		return ingredient;
	}

	public static IngredientJson emptyIngredientJson() {
		ingredientJson = new IngredientJson();
		return ingredientJson;
	}

	public static Ingredient idlessIngredient() {
		ingredient = new Ingredient();
		ingredient.setName(NAME);
		return ingredient;
	}

	public static IngredientJson idlessIngredientJson() {
		ingredientJson = new IngredientJson();
		ingredientJson.setName(NAME);
		return ingredientJson;
	}

	public static Ingredient fullyIngredient() {
		ingredient = new Ingredient(ID, NAME);
		return ingredient;
	}

	public static IngredientJson fullyIngredientJson() {
		ingredientJson = new IngredientJson(ID, NAME);
		return ingredientJson;
	}

	public static SortedSet<Ingredient> fiveIngredientsOnSortedSet() {
		ingredientSet = new TreeSet<>();
		for (int i = 1; i <= 5; i++) {
			ingredientSet.add(new Ingredient(ID + i, NAME + i));
		}
		return ingredientSet;
	}

	public static SortedSet<IngredientJson> fiveIngredientsJsonOnSortedSet() {
		ingredientJsonSet = new TreeSet<>();
		for (int i = 1; i <= 5; i++) {
			ingredientJsonSet.add(new IngredientJson(ID + i, NAME + i));
		}
		return ingredientJsonSet;
	}

	public static Recipe emptyRecipe() {
		recipe = new Recipe();
		return recipe;
	}

	public static RecipeJson emptyRecipeJson() {
		recipeJson = new RecipeJson();
		return recipeJson;
	}

	public static Recipe idlessRecipe() {
		recipe = new Recipe();
		recipe.setTitle(TITLE);
		recipe.setInstructions(INSTRUCTIONS);
		recipe.setIngredients(fiveIngredientsOnSortedSet());
		return recipe;
	}

	public static RecipeJson idlessRecipeJson() {
		recipeJson = new RecipeJson();
		recipeJson.setTitle(TITLE);
		recipeJson.setInstructions(INSTRUCTIONS);
		recipeJson.setIngredients(fiveIngredientsJsonOnSortedSet());
		return recipeJson;
	}

	public static Recipe fullyRecipe() {
		recipe = idlessRecipe();
		recipe.setId(ID);
		return recipe;
	}

	public static RecipeJson fullyRecipeJson() {
		recipeJson = idlessRecipeJson();
		recipeJson.setId(ID);
		return recipeJson;
	}

	public static String getId() {
		return ID;
	}

	public static String getName() {
		return NAME;
	}

	public static String getTitle() {
		return TITLE;
	}

	public static String getInstructions() {
		return INSTRUCTIONS;
	}

}
