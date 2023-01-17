package com.example.kmmfoodtofork.android.presentation.navigation.recipe_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmmfoodtofork.domain.model.GenericMessageInfo
import com.example.kmmfoodtofork.domain.model.UiComponentType
import com.example.kmmfoodtofork.domain.model.util.GenericMessageInfoUtil
import com.example.kmmfoodtofork.domain.model.util.Queue
import com.example.kmmfoodtofork.interactors.recipe_detail.GetRecipe
import com.example.kmmfoodtofork.presentation.recipe_detail.RecipeDetailState
import com.example.kmmfoodtofork.presentation.recipe_detail.RecipeDetailEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

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
            is RecipeDetailEvents.OnRemoveHeadFromQueue -> {
                removeHeadMessage()
            }
            else -> {
                val error = "Invalid Recipe"
                appendToMessageQueue(
                    GenericMessageInfo.Builder()
                        .id("SearchRecipes.Error")
                        .uiComponentType(UiComponentType.Dialog)
                        .title("Error")
                        .description(error)
                )
            }
        }
    }

    private fun loadRecipe(recipeId: Int) {
        getRecipe.execute(recipeId).onEach { dataState ->
            state.value = state.value?.copy(isLoading = dataState.isLoading)
            println("RecipeDetailVM [Loading] ${dataState.isLoading}")

            dataState.data?.let { recipe ->
                println("RecipeDetailVM $recipe")
                this.state.value = state.value?.copy(recipe = recipe)
            }

            dataState.message?.let { message ->
                appendToMessageQueue(message)

            }
        }.launchIn(viewModelScope)
    }

    /*    this is shit.. at the time it's believed that KMM doesn't allow extension
        functions to be used in swift hence this util class*/
    private fun appendToMessageQueue(messageInfo: GenericMessageInfo.Builder) {
        if (!state.value?.errorQueueDetailScreen?.let {
                GenericMessageInfoUtil()
                    .doesMessageAlreadyExistInQueue(
                        queue = it,
                        messageInfo = messageInfo.build()
                    )
            }!!
        ) {
            val queue = state.value?.errorQueueDetailScreen
            queue?.add(messageInfo.build())
            state.value = queue?.let { state.value?.copy(errorQueueDetailScreen = it) }
        }
    }

    private fun removeHeadMessage() {
        try {
            val queue = state.value?.errorQueueDetailScreen
            queue?.remove() // can throw exception if empty
            state.value =
                state.value?.copy(errorQueueDetailScreen = Queue(mutableListOf())) // force recompose
            state.value = state?.value?.copy(errorQueueDetailScreen = queue!!)
        } catch (e: Exception) {
            // nothing to remove, queue is empty
        }
    }
}
