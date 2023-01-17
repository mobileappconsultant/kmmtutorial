package com.example.kmmfoodtofork.interactors.recipe_list

import com.example.kmmfoodtofork.datasourc.network.RecipeService
import com.example.kmmfoodtofork.datasource.cache.RecipeCache
import com.example.kmmfoodtofork.domain.model.Recipe
import com.example.kmmfoodtofork.domain.model.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRecipe(
    private val recipeService: RecipeService,
    val recipeCache: RecipeCache
) {
    fun execute(page: Int, query: String): Flow<DataState<List<Recipe>>> = flow {
        //emit
        emit(DataState.loading())
        try {
            val recipes = recipeService.search(page, query)
            recipeCache.insert(recipes)
            val recipeFromCache = recipeCache.search(query = query, page = page)
            emit(DataState.data(null, recipeFromCache))
        } catch (ex: Exception) {
            emit(DataState.error(ex.message ?: "Unknown Error"))
        }
    }
}
