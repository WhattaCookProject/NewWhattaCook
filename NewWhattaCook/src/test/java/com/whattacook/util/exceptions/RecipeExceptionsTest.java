package com.whattacook.util.exceptions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RecipeExceptionsTest {

	@Test
	void testThrowsUpString() {
		assertThrows(RecipeExceptions.class, () -> RecipeExceptions.throwsUp("teste"));
	}

	@Test
	void testThrowsUp() {
		assertThrows(RecipeExceptions.class, () -> RecipeExceptions.throwsUp());
	}

}
