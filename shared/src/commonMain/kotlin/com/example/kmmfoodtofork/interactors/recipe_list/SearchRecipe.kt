package com.example.kmmfoodtofork.interactors.recipe_list

import com.example.kmmfoodtofork.datasourc.network.RecipeService
import com.example.kmmfoodtofork.datasource.cache.RecipeCache
import com.example.kmmfoodtofork.domain.model.Recipe
import com.example.kmmfoodtofork.domain.model.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRecipe(
    private val recipeService: RecipeService, private
    val recipeCache: RecipeCache
) {
    fun execute(page: Int, query: String): Flow<DataState<List<Recipe>>> = flow {
        //emit
        emit(DataState.loading())
        try {
            val recipes = recipeService.search(page, query)

            delay(500)
            recipeCache.insert(recipes)
            val cacheResult = if (query.isBlank()) {
                //BUG!
                recipeCache.getAll(page = page)
                //HACK TO FIX WHY IT'S RETURNING ONLY 1 ITEM
                emit(DataState.data(null, recipes))
            } else {
                recipeCache.search(query = query, page = page)
            }
            // emit(DataState.data(null, cacheResult))
        } catch (ex: Exception) {
            emit(DataState.error(ex.message ?: "Unknown Error"))
        }
    }
}
