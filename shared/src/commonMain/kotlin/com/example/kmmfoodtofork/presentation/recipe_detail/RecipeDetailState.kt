package com.example.kmmfoodtofork.presentation.recipe_detail

import com.example.kmmfoodtofork.domain.model.GenericMessageInfo
import com.example.kmmfoodtofork.domain.model.Recipe
import com.example.kmmfoodtofork.domain.model.util.Queue

data class RecipeDetailState(
    val isLoading: Boolean = false,
    val recipe: Recipe? = null,
    val errorQueueDetailScreen: Queue<GenericMessageInfo> = Queue(mutableListOf())
)
