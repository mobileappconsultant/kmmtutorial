package com.example.kmmfoodtofork.android.presentation.navigation.recipe_list.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.kmmfoodtofork.android.presentation.components.RECIPE_IMAGE_HEIGHT
import com.example.kmmfoodtofork.datasourc.network.RecipeServiceImpl.Companion.RECIPE_PAGINATION_PAGE_SIZE
import com.example.kmmfoodtofork.domain.model.Recipe

@Composable
fun RecipeList(
    loading: Boolean,
    recipes: List<Recipe>,
    onClickRecipeListItem: (Int) -> Unit,
    page: Int,
    onTriggerNextPage: () -> Unit
) {
    if (loading && recipes.isEmpty()) {
        LoadingRecipeListShimmer(imageHeight = RECIPE_IMAGE_HEIGHT.dp, padding = 5.dp)
    } else if (recipes.isEmpty()) {
        //there's nothing here
    } else {
        LazyColumn {
            itemsIndexed(items = recipes) { index, recipe ->
                if ((index + 1) >= page * RECIPE_PAGINATION_PAGE_SIZE && loading.not()) {
                    onTriggerNextPage.invoke()
                }


                RecipeCard(recipe = recipe, onClick = {
                    onClickRecipeListItem(recipe.id)
                })
            }
        }
    }
}
