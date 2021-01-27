package com.whattacook.model.ingredient;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.Rollback;

@DataMongoTest
@ExtendWith(SpringExtension.class)
@TestMethodOrder(OrderAnnotation.class)
class IngredientManagerTest {
	

	@BeforeEach
	public void setUpBeforeEach() throws Exception {
	}

}
