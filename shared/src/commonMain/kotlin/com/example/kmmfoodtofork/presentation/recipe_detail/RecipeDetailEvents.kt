package com.example.kmmfoodtofork.presentation.recipe_detail

import com.example.kmmfoodtofork.domain.model.Recipe

sealed class RecipeDetailEvents {
    data class GetRecipe(val recipeId: Int) : RecipeDetailEvents()
}
