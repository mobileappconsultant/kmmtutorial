package com.example.kmmfoodtofork.interactors.recipe_detail

import com.example.kmmfoodtofork.datasource.cache.RecipeCache
import com.example.kmmfoodtofork.domain.model.GenericMessageInfo
import com.example.kmmfoodtofork.domain.model.Recipe
import com.example.kmmfoodtofork.domain.model.UiComponentType
import com.example.kmmfoodtofork.domain.model.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRecipe(private val recipeCache: RecipeCache) {
    fun execute(id: Int): Flow<DataState<Recipe>> = flow {
        //emit
        emit(DataState.loading())
        delay(2000)
        try {
            val recipes = recipeCache.get(id)
            emit(DataState.data(null, recipes))
        } catch (ex: Exception) {
            emit(
                DataState.error(
                    GenericMessageInfo.Builder()
                        .id("GetRecipe.Error")
                        .uiComponentType(UiComponentType.Dialog)
                        .title("Error")
                        .description(ex.message ?: "Unknown Error")
                )
            )
        }
    }
}
