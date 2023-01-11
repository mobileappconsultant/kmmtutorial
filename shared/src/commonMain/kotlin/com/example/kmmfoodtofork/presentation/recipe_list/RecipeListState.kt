package com.example.kmmfoodtofork.presentation.recipe_list

import com.example.kmmfoodtofork.domain.model.Recipe

data class RecipeListState(
    val isLoading: Boolean = false,
    val page: Int = 1,
    val query: String = "",
    val recipes: List<Recipe> = listOf()
)
