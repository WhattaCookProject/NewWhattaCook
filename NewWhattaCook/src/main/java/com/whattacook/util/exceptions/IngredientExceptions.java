package com.whattacook.util.exceptions;

public class IngredientExceptions extends RuntimeException {

	private static final long serialVersionUID = -242765088910670710L;
	
	private static final String DETAILS = "Exception type Ingredient";

	public IngredientExceptions(String message) {
		super(String.format("%s : %s!!!", DETAILS, message));
	}
	
	public static Throwable throwsUp(String message) throws IngredientExceptions {
		throw new IngredientExceptions(message);
	};
	
	public static Throwable throwsUp() throws IngredientExceptions {
		throw new IngredientExceptions("with no message");
	};

}
