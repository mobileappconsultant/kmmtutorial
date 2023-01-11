package com.example.kmmfoodtofork.android.presentation.navigation.recipe_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmmfoodtofork.domain.model.Recipe
import com.example.kmmfoodtofork.interactors.recipe_detail.GetRecipe
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
    val recipe: MutableState<Recipe?> = mutableStateOf(null)

    init {
        savedStateHandle.get<Int>("recipeId")?.let { recipeId ->
            loadRecipe(recipeId)

        }

    }

    private fun loadRecipe(recipeId: Int){
        getRecipe.execute(recipeId).onEach {dataState->
            println("RecipeDetailVM [Loading] ${dataState.isLoading}")

            dataState.data?.let { recipe ->
                println("RecipeDetailVM $recipe")
                this.recipe.value = recipe
            }

            dataState.message?.let { message ->
                println("RecipeDetailVM $message")
            }
        }.launchIn(viewModelScope)
    }

}
