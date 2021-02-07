package com.whattacook.view.service;

import static com.whattacook.util.exceptions.RecipeExceptions.throwsUp;
import static com.whattacook.view.Response.recipeError303;
import static com.whattacook.view.Response.recipeError303NotFound;
import static com.whattacook.view.Response.voidError303;
import static com.whattacook.view.Response.voidError303NotFound;
import static com.whattacook.view.service.RecipeServiceValidation.updateByJson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.whattacook.model.recipe.Recipe;
import com.whattacook.model.recipe.RecipeJson;
import com.whattacook.model.recipe.RecipeManager;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
class RecipeServiceComponent {
	
	@Autowired
	private RecipeManager manager;

	Flux<RecipeJson> findAllRecipe() {
		return manager.findAll()
				.map(Recipe::toJson)
				.switchIfEmpty(Flux.error(throwsUp("Sorry, there's nothing to cook")));
	}

	Mono<ResponseEntity<RecipeJson>> findRecipeById(RecipeJson recipeJson) {
		return Mono.just(recipeJson.getId())
				.flatMap(manager::findById)
					.map(Recipe::toJson)
					.map(ResponseEntity::ok)
				.defaultIfEmpty(recipeError303NotFound())
				.onErrorReturn(recipeError303());
	}

	void ifTitleIsAlredyRegisteredThrowsException(RecipeJson recipeJson) {
		if (titleIsAlredyRegistered(recipeJson))
			throwsUp("This Recipe is already registered!");
	}

	boolean titleIsAlredyRegistered(RecipeJson recipeJson) {
		return manager.existsByTitleIgnoreCase(recipeJson.getTitle()).block();
	}

	Mono<ResponseEntity<RecipeJson>> saveNewRecipe(RecipeJson recipeJson) {
		return Mono.just(recipeJson)
				.map(RecipeJson::toRecipe)
				.flatMap(manager::save)
				.map(Recipe::toJson)
				.map(ResponseEntity::ok)
				.onErrorReturn(recipeError303());
	}

	Mono<ResponseEntity<RecipeJson>> updateRecipe(RecipeJson recipeJson) {
		return Mono.just(recipeJson.getId())
				.flatMap(manager::findById)
					.map(recipe -> updateByJson(recipe, recipeJson))
					.flatMap(manager::save)
						.map(Recipe::toJson)
						.map(ResponseEntity::ok)
				.defaultIfEmpty(recipeError303NotFound())
				.onErrorReturn(recipeError303());
	}

	Mono<ResponseEntity<Void>> deleteRecipe(RecipeJson recipeJson) {
		return Mono.just(recipeJson.getId())
				.flatMap(manager::findById)
					.flatMap(manager::delete)
						.map(ResponseEntity::ok)
				.defaultIfEmpty(voidError303NotFound())
				.onErrorReturn(voidError303());
	}

}
