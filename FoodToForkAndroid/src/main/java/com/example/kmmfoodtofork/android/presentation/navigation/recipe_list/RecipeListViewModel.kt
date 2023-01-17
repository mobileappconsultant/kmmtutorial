package com.example.kmmfoodtofork.android.presentation.navigation.recipe_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmmfoodtofork.domain.model.GenericMessageInfo
import com.example.kmmfoodtofork.domain.model.NegativeAction
import com.example.kmmfoodtofork.domain.model.PositiveAction
import com.example.kmmfoodtofork.domain.model.Recipe
import com.example.kmmfoodtofork.domain.model.UiComponentType
import com.example.kmmfoodtofork.domain.model.util.GenericMessageInfoUtil
import com.example.kmmfoodtofork.domain.model.util.Queue
import com.example.kmmfoodtofork.interactors.recipe_list.SearchRecipe
import com.example.kmmfoodtofork.presentation.recipe_list.FoodCategory
import com.example.kmmfoodtofork.presentation.recipe_list.RecipeListEvents
import com.example.kmmfoodtofork.presentation.recipe_list.RecipeListState
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
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

            is RecipeListEvents.OnRemoveHeadFromQueue -> {
                removeHeadMessage()
            }
            RecipeListEvents.NewSearchEvent -> {
                newSearch()
            }

            is RecipeListEvents.OnUpdateQuery -> {
                state.value = state.value.copy(query = events.updatedQuery, selectedCategory = null)
            }

            is RecipeListEvents.OnCategorySelect -> {
                onSelectCategory(events.selectedCategory)
            }
            else -> {
                appendToMessageQueue(
                    GenericMessageInfo.Builder()
                        .id("SearchRecipes.Error")
                        .uiComponentType(UiComponentType.Dialog)
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
        if (!state.value?.errorQueueListScreen?.let {
                GenericMessageInfoUtil()
                    .doesMessageAlreadyExistInQueue(
                        queue = it,
                        messageInfo = messageInfo.build()
                    )
            }!!
        ) {
            val queue = state.value?.errorQueueListScreen
            queue?.add(messageInfo.build())
            state.value = queue?.let { state.value?.copy(errorQueueListScreen = it) }!!
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
            .onEach { dataState ->
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

            }.launchIn(viewModelScope)
    }

    private fun appendRecipes(recipes: List<Recipe>) {
        var current = state.value.recipes.toMutableList()
        current.addAll(recipes)
        state.value = state.value.copy(recipes = current)
    }

    private fun removeHeadMessage() {
        try {
            val queue = state.value.errorQueueListScreen
            queue.remove() // can throw exception if empty
            state.value =
                state.value.copy(errorQueueListScreen = Queue(mutableListOf())) // force recompose
            state.value = state.value.copy(errorQueueListScreen = queue)
        } catch (e: Exception) {
            // nothing to remove, queue is empty
        }
    }


}
