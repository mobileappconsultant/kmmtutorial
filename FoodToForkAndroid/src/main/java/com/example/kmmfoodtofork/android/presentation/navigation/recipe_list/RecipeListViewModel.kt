package com.example.kmmfoodtofork.android.presentation.navigation.recipe_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmmfoodtofork.domain.model.Recipe
import com.example.kmmfoodtofork.interactors.recipe_list.SearchRecipe
import com.example.kmmfoodtofork.presentation.recipe_list.RecipeListEvents
import com.example.kmmfoodtofork.presentation.recipe_list.RecipeListState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val searchRecipe: SearchRecipe
) : ViewModel() {
    val state: MutableState<RecipeListState> = mutableStateOf(RecipeListState())
    private var job: Job? = null

    init {
        job = Job()
        onTriggerEvent(RecipeListEvents.LoadRecipes)
    }

    fun onTriggerEvent(events: RecipeListEvents) {
        when (events) {
            RecipeListEvents.LoadRecipes -> {
                loadRecipes()
            }
            RecipeListEvents.NextPage -> {
                nextPage()
            }

            RecipeListEvents.NewSearchEvent -> {
                newSearch()
            }

            is RecipeListEvents.OnUpdateQuery -> {
                state.value = state.value.copy(query = events.updatedQuery)
            }
            else -> {
                handleError("Invalid Event")
            }
        }
    }

    private fun handleError(errorMessage: String) {
        //TODO handle the error
    }

    private fun nextPage() {
        state.value = state.value.copy(page = state.value.page.plus(1))
        loadRecipes()
    }

    private fun newSearch() {
        state.value = state.value.copy(page = 1, recipes = listOf())
        loadRecipes()
    }

    private fun loadRecipes() {
        if (job?.isCancelled == true) {
            job = Job()
        }

        viewModelScope.launch(job!!) {
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
                        handleError(message)
                    }

                }.collect()/*.launchIn(viewModelScope)*/
        }
    }

    private fun appendRecipes(recipes: List<Recipe>) {
        var current = state.value.recipes.toMutableList()
        current.addAll(recipes)
        state.value = state.value.copy(recipes = current)
    }


}
