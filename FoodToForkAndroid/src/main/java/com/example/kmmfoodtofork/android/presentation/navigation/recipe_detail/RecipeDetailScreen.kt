package com.example.kmmfoodtofork.android.presentation.navigation.recipe_detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.kmmfoodtofork.android.presentation.navigation.Screen
import com.example.kmmfoodtofork.domain.model.Recipe

@Composable
fun RecipeDetailScreen(recipe: Recipe) {
    if (recipe == null) {
        Text("ERROR")
    } else {
        Text(text = "RecipeDetailScreen:${recipe.title}")
    }
}
