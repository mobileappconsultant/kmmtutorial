package com.example.kmmfoodtofork.android.presentation.navigation.recipe_list.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import com.example.kmmfoodtofork.domain.model.Recipe

@Composable
fun RecipeList(loading: Boolean, recipes: List<Recipe>, onClickRecipeListItem: (Int) -> Unit) {
    if (loading && recipes.isEmpty()) {

    } else if (recipes.isEmpty()) {

    } else {
        LazyColumn {
            itemsIndexed(items = recipes) { index, recipe ->
                RecipeCard(recipe = recipe, onClick = {
                    onClickRecipeListItem(recipe.id)
                })
            }
        }
    }
}
