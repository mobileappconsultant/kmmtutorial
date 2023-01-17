package com.example.kmmfoodtofork.android.presentation.navigation.recipe_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmmfoodtofork.interactors.recipe_detail.GetRecipe
import com.example.kmmfoodtofork.presentation.recipe_detail.RecipeDetailState
import com.example.kmmfoodtofork.presentation.recipe_detail.RecipeDetailEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@OptIn(ExperimentalStdlibApi::class)
@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getRecipe: GetRecipe
) :
    ViewModel() {
    val state: MutableState<RecipeDetailState?> = mutableStateOf(RecipeDetailState())

    init {
        savedStateHandle.get<Int>("recipeId")?.let { recipeId ->
            onTriggerEvent(RecipeDetailEvents.GetRecipe(recipeId = recipeId))
        }
    }

     fun onTriggerEvent(recipeDetailEvents: RecipeDetailEvents) {
        when (recipeDetailEvents) {
            is RecipeDetailEvents.GetRecipe -> {
                loadRecipe(recipeDetailEvents.recipeId)
            }
            else -> {
                handleError("Invalid State")
            }
        }
    }

    private fun loadRecipe(recipeId: Int) {
        getRecipe.execute(recipeId).onEach { dataState ->
            state.value  = state.value?.copy(isLoading = dataState.isLoading)
            println("RecipeDetailVM [Loading] ${dataState.isLoading}")

            dataState.data?.let { recipe ->
                println("RecipeDetailVM $recipe")
                this.state.value = state.value?.copy(recipe = recipe)
            }

            dataState.message?.let { message ->
                println("RecipeDetailVM $message")
            }
        }.launchIn(viewModelScope)
    }

    private fun handleError(error: String) {
        //TODO
    }
}
