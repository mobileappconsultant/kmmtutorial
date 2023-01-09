package com.example.kmmfoodtofork.datasourc.network

import com.example.kmmfoodtofork.domain.model.Recipe

interface RecipeService {
    suspend fun search(page: Int, query: String): List<Recipe>
    suspend fun getRecipe(id: Int): Recipe
}
