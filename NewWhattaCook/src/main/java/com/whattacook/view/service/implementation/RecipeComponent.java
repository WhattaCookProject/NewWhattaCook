package com.whattacook.view.service.implementation;

import static com.whattacook.util.exceptions.RecipeExceptions.throwsUp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.whattacook.model.recipe.Recipe;
import com.whattacook.model.recipe.RecipeJson;
import com.whattacook.model.recipe.RecipeManager;

import reactor.core.publisher.Flux;

@Component
class RecipeComponent {
	
	@Autowired
	private RecipeManager manager;

	Flux<RecipeJson> findAllRecipe() {
		return manager.findAll()
				.map(Recipe::toJson)
				.switchIfEmpty(Flux.error(throwsUp("Sorry, there's nothing to cook")));
	}

}
