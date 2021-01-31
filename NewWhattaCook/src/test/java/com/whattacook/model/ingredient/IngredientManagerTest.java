package com.whattacook.model.ingredient;

import static com.whattacook.model.KitchenFactory.fiveIngredientsOnSortedSet;
import static com.whattacook.model.KitchenFactory.getId;
import static com.whattacook.model.KitchenFactory.getName;
import static com.whattacook.model.KitchenFactory.idlessIngredient;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.SortedSet;
import java.util.TreeSet;

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

import reactor.core.publisher.Flux;

@DataMongoTest
@ExtendWith(SpringExtension.class)
@TestMethodOrder(OrderAnnotation.class)
class IngredientManagerTest {
	
	@Autowired
	private IngredientManager manager;
	
	
	private final String NOT_EXIST = getId();
	private final String NAME = getName(); 
	private final Ingredient ingredient = idlessIngredient();
	private final SortedSet<Ingredient> ingredientSet = fiveIngredientsOnSortedSet();
	private static String idFromDB;
	private Boolean exist;
	private Ingredient toCompare;

	@BeforeEach
	public void setUpBeforeEach() throws Exception {
		toCompare = null;
		exist = null;
	}
	
	@Test
	@Order(1)
	@DisplayName("Save Ingrediente")
	final void testSave() {
		toCompare = manager.save(ingredient).block();
		
		assertAll(
				() -> assertNotNull(toCompare, "NotNull 1"),
				() -> assertNotNull(toCompare.getId(), "NotNull 2")
				);
		
		idFromDB = toCompare.getId();
		ingredient.setId(idFromDB);
		
		assertEquals(ingredient, toCompare, "Equals 1");
		
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

	@Test
	@Order(4)
	@DisplayName("Find Ingrediente By Name Ignore Case - FALSE")
	final void test01_FindByNameIgnoreCase() {
		exist = manager.findByNameIgnoreCase(NOT_EXIST).hasElement().block();
		assertFalse(exist, "Should be false, because it doesn't exist in the database!");
	}
	
	@Test
	@Order(5)
	@DisplayName("Find Ingrediente By Name Ignore Case")
	final void test02_FindByNameIgnoreCase() {
		toCompare = manager.findByNameIgnoreCase(NAME).block();
		ingredient.setId(idFromDB);
		assertEquals(ingredient, toCompare);
	}

	@Test
	@Order(6)
	@DisplayName("Find Ingrediente By ID")
	final void test01_FindByIdID() {
		toCompare = manager.findById(idFromDB).block();
		ingredient.setId(idFromDB);
		assertEquals(ingredient, toCompare);
	}
	
	@Test
	@Order(7)
	@DisplayName("Find Ingrediente By ID - False")
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
	
	@Test
	@Order(10)
	@DisplayName("Save All")
	final void testSaveAll() {
		Flux<Ingredient> saved = manager.saveAll(ingredientSet);
		assertAll(
				() -> assertEquals(5, saved.count().block()),
				() -> assertEquals(6, manager.count().block())
				);
	}
	
	@Test
	@Order(11)
	@DisplayName("Find All")
	final void testFindAll() {
		SortedSet<Ingredient> findAll = new TreeSet<>(manager.findAll().collectSortedList().block());
		ingredientSet.add(ingredient);
		assertEquals(ingredientSet, findAll);
		
	}

}
