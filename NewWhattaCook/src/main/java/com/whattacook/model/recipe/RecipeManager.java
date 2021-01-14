package com.whattacook.model.recipe;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeManager extends ReactiveMongoRepository<Recipe, String>{

}
