package com.example.kmmfoodtofork.android.presentation.navigation.recipe_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle) :
    ViewModel() {

}
