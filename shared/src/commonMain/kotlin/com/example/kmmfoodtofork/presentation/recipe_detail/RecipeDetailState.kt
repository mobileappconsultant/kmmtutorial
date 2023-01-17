package com.example.kmmfoodtofork.presentation.recipe_detail

import com.example.kmmfoodtofork.domain.model.Recipe

data class RecipeDetailState(val isLoading: Boolean=false, val recipe: Recipe?=null)
