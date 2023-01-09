package com.example.kmmfoodtofork.interactors.recipe_detail

import com.example.kmmfoodtofork.datasourc.network.RecipeService
import com.example.kmmfoodtofork.domain.model.Recipe
import com.example.kmmfoodtofork.domain.model.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRecipe(private val recipeService: RecipeService) {
    fun execute(id: Int): Flow<DataState<Recipe>> = flow {
        //emit
        emit(DataState.loading())
        try {
            val recipes = recipeService.getRecipe(id)
            emit(DataState.data(null, recipes))
        } catch (ex: Exception) {
            emit(DataState.error(ex.message ?: "Unknown Error"))
        }
    }
}
