package com.example.kmmfoodtofork.presentation.recipe_list

import com.example.kmmfoodtofork.domain.model.GenericMessageInfo
import com.example.kmmfoodtofork.domain.model.Recipe
import com.example.kmmfoodtofork.domain.util.Queue

actual data class RecipeListState(
    val isLoading: Boolean = false,
    val page: Int = 1, val query: String = "",
    val selectedCategory: FoodCategory? = null,
    val recipes: List<Recipe> = listOf(),
    val bottomRecipe: Recipe? = null,
    val queue: Queue<GenericMessageInfo> = Queue(mutableListOf())
) {
    constructor() : this(
        isLoading = false,
        page = 1,
        query = "",
        bottomRecipe = null,
        selectedCategory = null,
        recipes = listOf(),
        queue = Queue(
            mutableListOf()
        )
    )

    companion object {
        const val RECIPE_PAGINATION_PAGE_SIZE = 30
    }
}
