package com.whattacook.model.recipe;

import static com.whattacook.model.KitchenFactory.getId;
import static com.whattacook.model.KitchenFactory.getInstructions;
import static com.whattacook.model.KitchenFactory.getTitle;
import static com.whattacook.model.KitchenFactory.idlessRecipe;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@DataMongoTest
@ExtendWith(SpringExtension.class)
@TestMethodOrder(OrderAnnotation.class)
class RecipeManagerTest {

	@Autowired
	private RecipeManager manager;

	private final String NOT_EXIST = getId();
	private final String TITLE = getTitle(); 
	private final String INSTRUCTIONS = getInstructions(); 
	private final Recipe recipe = idlessRecipe();
	private static String idFromDB;
	private Boolean exist;
	private Recipe toCompare;
	
	@BeforeEach
	public void setUpBeforeEach() throws Exception {
		toCompare = null;
		exist = null;
	}
	
	@Test
	@Order(1)
	@DisplayName("Save Recipe")
	final void testSave() {
		toCompare = manager.save(recipe).block();
		
		assertAll(
				() -> assertNotNull(toCompare, "NotNull 1"),
				() -> assertNotNull(toCompare.getId(), "NotNull 2")
				);
		
		idFromDB = toCompare.getId();
		recipe.setId(idFromDB);
		
		assertEquals(recipe, toCompare, "Equals 1");
		
	}
	
	@Test
	@Order(2)
	@DisplayName("Exist Recipe By Title Ignore Case - FALSE")
	final void test01_ExistsByTitleIgnoreCase() {
		exist = manager.existsByTitleIgnoreCase(NOT_EXIST).block();
		assertFalse(exist, "Should be false, because it doesn't exist in the database!");
	}
	
	@Test
	@Order(3)
	@DisplayName("Exist Recipe By Title Ignore Case - TRUE")
	final void test02_ExistsByTitleIgnoreCase() {
		exist = manager.existsByTitleIgnoreCase(TITLE).block();
		assertTrue(exist, "Should be true, because it exists in the database!");
	}
	
	@Test
	@Order(4)
	@DisplayName("Find Recipe By Title Ignore Case - FALSE")
	final void test01_FindByTitleIgnoreCase() {
		/*
		 * el metodo "findByTitleContainingIgnoreCase" devuelve un flux en vez de un Mono
		 *(para el caso de los ingredientes) . Por qué devuelve un Flux en vez de un Mono?
		*/
		exist = manager.findByTitleContainingIgnoreCase(NOT_EXIST).hasElements().block(); 
		assertFalse(exist, "Should be false, because it doesn't exist in the database!");
	}
	
	@Test
	@Order(5)
	@DisplayName("Find Recipe By Title Ignore Case - TRUE")
	final void test02_FindByTitleIgnoreCase() {
		/*
		 * Lo mismo pasó aquí, cambié de ".block()" a ".blockFirst()" para pasar de Flux a Recipe
		 */
		toCompare = manager.findByTitleContainingIgnoreCase(TITLE).blockFirst();
		recipe.setId(idFromDB);
		recipe.setInstructions(INSTRUCTIONS);
		assertEquals(recipe, toCompare);
	}
	
	@Test
	@Order(6)
	@DisplayName("Find Recipe By ID")
	final void test01_FindByIdID() {
		toCompare = manager.findById(idFromDB).block();
		recipe.setId(idFromDB);
		recipe.setInstructions(INSTRUCTIONS);
		assertEquals(recipe, toCompare);
	}
	
	@Test
	@Order(7)
	@DisplayName("Find Recipe By ID - False")
	final void test02_FindByIdID() {
		exist = manager.findById(NOT_EXIST).hasElement().block();
		assertFalse(exist, "Should be false, because it doesn't exist in the database!");
	}
	
	@Test
	@Order(8)
	@DisplayName("Exists By ID - TRUE")
	final void test01_ExistsByIdID() {
		exist = manager.existsById(idFromDB).block();
		assertTrue(exist, "Should be True, because it exist in the database!");
	}
	
	@Test
	@Order(9)
	@DisplayName("Exists By ID - FALSE")
	final void test02_ExistsByIdID() {
		exist = manager.existsById(NOT_EXIST).block();
		assertFalse(exist, "Should be false, because it doesn't exist in the database!");
	}

	
}
