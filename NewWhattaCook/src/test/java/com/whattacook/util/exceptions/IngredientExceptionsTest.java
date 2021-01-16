package com.whattacook.util.exceptions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IngredientExceptionsTest {

	@Test
	void testThrowsUpString() {
		assertThrows(IngredientExceptions.class, () -> IngredientExceptions.throwsUp("teste"));
	}

	@Test
	void testThrowsUp() {
		assertThrows(IngredientExceptions.class, () -> IngredientExceptions.throwsUp());
	}

}
