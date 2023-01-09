package com.example.kmmfoodtofork.android.presentation.navigation.recipe_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmmfoodtofork.interactors.recipe_list.SearchRecipe
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val searchRecipe: SearchRecipe
) :
    ViewModel() {
    init {
        loadRecipes()
    }

    private fun loadRecipes() {
        searchRecipe.execute(page = 1, query = "Chicken")
            .onEach { dataState ->
                println("RecipeListVM [Loading] ${dataState.isLoading}")

                dataState.data?.let { recipes ->
                    println("RecipeListVM $recipes")
                }

                dataState.message?.let { message ->
                    println("RecipeListVM $message")
                }
            }.launchIn(viewModelScope)
    }
}
