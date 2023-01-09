package com.example.kmmfoodtofork.datasource.cache

import com.example.kmmfoodtofork.domain.model.Recipe

interface RecipeCache {
    fun insertRecipe(recipe: Recipe)
    fun insert(recipes: List<Recipe>)
    fun search(query: String, page: Int): List<Recipe>
    fun getAll(page: Int): List<Recipe>

    @Throws(NullPointerException::class)
    fun get(recipeId: Int): Recipe?
}
