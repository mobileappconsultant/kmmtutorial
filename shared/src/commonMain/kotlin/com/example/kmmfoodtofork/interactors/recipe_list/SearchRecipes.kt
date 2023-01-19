package com.example.kmmfoodtofork.interactors.recipe_list


import com.example.kmmfoodtofork.datasource.cache.RecipeCache
import com.example.kmmfoodtofork.datasource.network.RecipeService
import com.example.kmmfoodtofork.domain.model.GenericMessageInfo
import com.example.kmmfoodtofork.domain.model.Recipe
import com.example.kmmfoodtofork.domain.model.UIComponentType
import com.example.kmmfoodtofork.domain.util.CommonFlow
import com.example.kmmfoodtofork.domain.util.DataState
import com.example.kmmfoodtofork.domain.util.asCommonFlow
import kotlinx.coroutines.flow.Flow

import kotlinx.coroutines.flow.flow


class SearchRecipe(
    private val recipeService: RecipeService,
    val recipeCache: RecipeCache
) {
    fun execute(page: Int, query: String): CommonFlow<DataState<List<Recipe>>> = flow {
        //emit
        emit(DataState.loading())
        try {
            val recipes = recipeService.search(page, query)
            if (query == "error") {
                throw Exception("Forcing error.. Search Failed")
            }
            recipeCache.insert(recipes)
            val recipeFromCache = recipeCache.search(query = query, page = page)
            emit(DataState.data(null, recipeFromCache))
        } catch (ex: Exception) {
            emit(
                DataState.error(
                    GenericMessageInfo.Builder()
                        .id("SearchRecipes.Error")
                        .uiComponentType(UIComponentType.Dialog)
                        .title("Error")
                        .description(ex.message ?: "Unknown Error")
                )
            )
        }
    }.asCommonFlow()
}

