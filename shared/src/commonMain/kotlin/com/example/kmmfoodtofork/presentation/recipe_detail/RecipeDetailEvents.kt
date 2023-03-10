package com.example.kmmfoodtofork.presentation.recipe_detail

sealed class RecipeDetailEvents {
    data class GetRecipe(val recipeId: Int) : RecipeDetailEvents()
    object OnRemoveHeadFromQueue : RecipeDetailEvents()
}
