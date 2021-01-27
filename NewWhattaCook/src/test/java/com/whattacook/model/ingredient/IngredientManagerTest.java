package com.whattacook.model.ingredient;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataMongoTest
@ExtendWith(SpringExtension.class)
@TestMethodOrder(OrderAnnotation.class)
class IngredientManagerTest {
	
	@Autowired
	private IngredientManager manager;
	
	
	private final String NOT_EXIST = "string impossible in DB";
	private final String NAME = "AbraCadaBra"; 
	private final Ingredient ingredient = new Ingredient(id, NAME);
	private static String id;
	private Boolean exist;
	private Ingredient toCompare;

	@BeforeEach
	public void setUpBeforeEach() throws Exception {
		toCompare = null;
		exist = null;
	}
	
	@Test
	@Order(1)
	@Rollback(false)
	@DisplayName("Save Ingrediente")
	final void testSave() {
		toCompare = manager.save(ingredient).block();
		assertNotNull(toCompare);
		id = toCompare.getId();
		assertNotNull(id);
		ingredient.setId(id);
		assertEquals(ingredient, toCompare);
		
	}

	@Test
	@Order(2)
	@DisplayName("Exist Ingrediente By Name Ignore Case - FALSE")
	final void test01_ExistsByNameIgnoreCase() {
		exist = manager.existsByNameIgnoreCase(NOT_EXIST).block();
		assertFalse(exist, "Should be false, because it doesn't exist in the database!");
	}
	
	@Test
	@Order(3)
	@DisplayName("Exist Ingrediente By Name Ignore Case - TRUE")
	final void test02_ExistsByNameIgnoreCase() {
		exist = manager.existsByNameIgnoreCase(NAME).block();
		assertTrue(exist, "Should be false, because it doesn't exist in the database!");
	}


}
