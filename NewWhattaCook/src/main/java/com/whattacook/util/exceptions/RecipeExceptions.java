package com.whattacook.util.exceptions;

public class RecipeExceptions extends RuntimeException {

	private static final long serialVersionUID = -8696035764046960567L;

	private static final String DETAILS = "Exception type Recipe";

	public RecipeExceptions(String message) {
		super(String.format("%s : %s!!!", DETAILS, message));
	}
	
	public static Throwable throwsUp(String message) throws IngredientExceptions {
		throw new RecipeExceptions(message);
	};
	
	public static Throwable throwsUp() throws IngredientExceptions {
		throw new RecipeExceptions("with no message");
	};
}
