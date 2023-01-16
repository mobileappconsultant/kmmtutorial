package com.example.kmmfoodtofork.presentation.recipe_list

sealed class RecipeListEvents {

    object LoadRecipes : RecipeListEvents()

    object NextPage : RecipeListEvents()

    object NewSearchEvent : RecipeListEvents()

    data class OnUpdateQuery(val updatedQuery: String) : RecipeListEvents()
}
