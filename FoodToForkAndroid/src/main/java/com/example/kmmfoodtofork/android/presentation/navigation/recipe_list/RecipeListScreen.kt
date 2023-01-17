package com.example.kmmfoodtofork.android.presentation.navigation.recipe_list

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import com.example.kmmfoodtofork.android.presentation.navigation.recipe_list.components.SearchAppBar
import com.example.kmmfoodtofork.android.presentation.navigation.theme.AppTheme
import com.example.kmmfoodtofork.presentation.recipe_list.FoodCategoryUtil
import com.example.kmmfoodtofork.presentation.recipe_list.RecipeListEvents
import com.example.kmmfoodtofork.presentation.recipe_list.RecipeListState

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
@Composable
fun RecipeList(
    state: RecipeListState,
    onTriggerEvent: (RecipeListEvents) -> Unit,
    onClickRecipeListItem: (Int) -> Unit
) {

    AppTheme(displayProgressBar = false, dialogQueue = state.errorQueueListScreen,
        onRemoveHeadMessageFromQueue = {
            onTriggerEvent(RecipeListEvents.OnRemoveHeadFromQueue)
        }) {
        val foodCategories = remember {
            FoodCategoryUtil().getAllFoodCategories()
        }
        Scaffold(topBar = {
            SearchAppBar(
                query = state.query,
                onQueryChanged = {
                    onTriggerEvent(RecipeListEvents.OnUpdateQuery(it))
                },
                onExecuteSearch = {
                    onTriggerEvent(RecipeListEvents.NewSearchEvent)
                },
                categories = foodCategories,
                selectedCategory = state.selectedCategory,
                onSelectedCategoryChanged = {
                    onTriggerEvent(RecipeListEvents.OnCategorySelect(it))
                },
            )
        }
        ) {
            com.example.kmmfoodtofork.android.presentation.navigation.recipe_list.components.RecipeList(
                loading = state.isLoading,
                recipes = state.recipes,
                onClickRecipeListItem = onClickRecipeListItem,
                page = state.page,
                onTriggerNextPage = {
                    onTriggerEvent(RecipeListEvents.NextPage)
                })
        }
    }
}

