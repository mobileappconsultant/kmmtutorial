package com.example.kmmfoodtofork.android.presentation.navigation.recipe_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmmfoodtofork.domain.model.Recipe
import com.example.kmmfoodtofork.interactors.recipe_list.SearchRecipe
import com.example.kmmfoodtofork.presentation.recipe_list.RecipeListState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val searchRecipe: SearchRecipe
) : ViewModel() {
     val state: MutableState<RecipeListState> = mutableStateOf(RecipeListState())

    init {
        loadRecipes()
    }

    private fun loadRecipes() {
        searchRecipe.execute(page = state.value.page, query = state.value.query)
            .onEach { dataState ->
                println("RecipeListVM [Loading] ${dataState.isLoading}")
                state.value = state.value.copy(isLoading = dataState.isLoading)
                dataState.data?.let { recipes ->
                    println("RecipeListVM $recipes")
                    appendRecipes(recipes)
                }
                dataState.message?.let { message ->
                    println("RecipeListVM $message")
                }
            }.launchIn(viewModelScope)
    }

    private fun appendRecipes(recipes: List<Recipe>) {
        var current = state.value.recipes.toMutableList()
        current.addAll(recipes)
        state.value = state.value.copy(recipes = current)
    }
}
