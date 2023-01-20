package com.example.kmmfoodtofork.android.presentation.navigation.recipe_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmmfoodtofork.presentation.recipe_list.FoodCategory
import com.example.kmmfoodtofork.presentation.recipe_list.RecipeListEvents
import com.example.kmmfoodtofork.presentation.recipe_list.RecipeListState
import com.example.kmmfoodtofork.domain.model.GenericMessageInfo
import com.example.kmmfoodtofork.domain.model.Recipe
import com.example.kmmfoodtofork.domain.model.UIComponentType
import com.example.kmmfoodtofork.domain.util.GenericMessageInfoQueueUtil
import com.example.kmmfoodtofork.domain.util.Queue
import com.example.kmmfoodtofork.interactors.recipe_list.SearchRecipe

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val searchRecipe: SearchRecipe
) : ViewModel() {
    val state: MutableState<RecipeListState> = mutableStateOf(RecipeListState())

    init {
        onTriggerEvent(RecipeListEvents.LoadRecipes)

        // EXAMPLE
        /*       val messageInfoBuilder = GenericMessageInfo.Builder()
                   .id(UUID.randomUUID().toString())
                 .title("Weird")
                   .uiComponentType(UiComponentType.Dialog)
                   .description("I don't know what's happening.")
                   .positive(PositiveAction(
                       positiveBtnTxt = "OK",
                       onPositiveAction = {
                            //do something special??
                           state.value = state.value.copy(query = "Kale")
                           onTriggerEvent(RecipeListEvents.NewSearchEvent)
                       }
                   ))
                   .negative(NegativeAction(
                       negativeBtnTxt = "Cancel",
                       onNegativeAction = {
                           state.value = state.value.copy(query = "Cookies")
                           onTriggerEvent(RecipeListEvents.NewSearchEvent)
                       }
                   ))
               appendToMessageQueue(messageInfo = messageInfoBuilder)*/
    }

    fun onTriggerEvent(events: RecipeListEvents) {
        when (events) {
            RecipeListEvents.LoadRecipes -> {
                loadRecipes()
            }
            RecipeListEvents.NextPage -> {
                nextPage()
            }

            is RecipeListEvents.OnRemoveHeadMessageFromQueue -> {
                removeHeadMessage()
            }
            RecipeListEvents.NewSearch -> {
                newSearch()
            }

            is RecipeListEvents.OnUpdateQuery -> {
                state.value = state.value.copy(query = events.query, selectedCategory = null)
            }

            is RecipeListEvents.OnSelectCategory -> {
                onSelectCategory(events.category)
            }
            else -> {
                appendToMessageQueue(
                    GenericMessageInfo.Builder()
                        .id("SearchRecipes.Error")
                        .uiComponentType(UIComponentType.Dialog)
                        .title("Error")
                        .description("Invalid Event")

                )
            }
        }
    }

    private fun onSelectCategory(category: FoodCategory) {
        state.value = state.value.copy(selectedCategory = category, query = category.value)
        newSearch()
    }

    /*    this is shit.. at the time it's believed that KMM doesn't allow extension
        functions to be used in swift hence this util class*/
    private fun appendToMessageQueue(messageInfo: GenericMessageInfo.Builder) {
        if (!state.value.queue.let {
                GenericMessageInfoQueueUtil()
                    .doesMessageAlreadyExistInQueue(
                        queue = it,
                        messageInfo = messageInfo.build()
                    )
            }
        ) {
            val queue = state.value.queue
            queue.add(messageInfo.build())
            state.value = queue.let { state.value.copy(queue = it) }
        }
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
        searchRecipe.execute(page = state.value.page, query = state.value.query)
            .collectCommon(viewModelScope) { dataState ->
                println("RecipeListVM [Loading] ${dataState.isLoading}")
                state.value = state.value.copy(isLoading = dataState.isLoading)
                dataState.data?.let { recipes ->
                    println("RecipeListVM $recipes")
                    appendRecipes(recipes)
                }
                dataState.message?.let { message ->
                    println("RecipeListVM $message")
                    appendToMessageQueue(message)
                }

            }
    }

    private fun appendRecipes(recipes: List<Recipe>) {
        var current = state.value.recipes.toMutableList()
        current.addAll(recipes)
        state.value = state.value.copy(recipes = current)
    }

    private fun removeHeadMessage() {
        try {
            val queue = state.value.queue
            queue.remove() // can throw exception if empty
            state.value =
                state.value.copy(queue = Queue(mutableListOf())) // force recompose
            state.value = state.value.copy(queue = queue)
        } catch (e: Exception) {
            // nothing to remove, queue is empty
        }
    }


}
