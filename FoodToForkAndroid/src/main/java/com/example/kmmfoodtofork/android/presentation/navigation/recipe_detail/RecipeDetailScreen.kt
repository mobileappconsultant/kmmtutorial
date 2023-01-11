package com.example.kmmfoodtofork.android.presentation.navigation.recipe_detail

import android.text.Html
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.core.text.HtmlCompat
import com.example.kmmfoodtofork.android.presentation.components.RecipeImage
import com.example.kmmfoodtofork.android.presentation.navigation.Screen
import com.example.kmmfoodtofork.android.presentation.navigation.recipe_list.components.RecipeCard
import com.example.kmmfoodtofork.android.presentation.navigation.theme.AppTheme
import com.example.kmmfoodtofork.domain.model.Recipe

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
@Composable
fun RecipeDetailScreen(recipe: Recipe) {
    AppTheme(displayProgressBar = false) {
        if (recipe == null) {
            Text("ERROR", style = MaterialTheme.typography.caption)
        } else {
            RecipeCard(recipe=recipe, onClick = {})
        }
    }
}
