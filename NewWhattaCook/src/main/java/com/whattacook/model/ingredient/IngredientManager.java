package com.whattacook.model.ingredient;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientManager extends ReactiveMongoRepository<Ingredient, String>{

}
