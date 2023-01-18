package com.example.kmmfoodtofork.datasource.network

import com.example.kmmfoodtofork.domain.model.Recipe

interface RecipeService {

    suspend fun search(
        page: Int,
        query: String,
    ): List<Recipe>

    suspend fun get(
        id: Int
    ): Recipe
}
