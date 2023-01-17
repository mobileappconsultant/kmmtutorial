@file:OptIn(ExperimentalComposeUiApi::class)

package com.example.kmmfoodtofork.android.presentation.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.kmmfoodtofork.android.presentation.navigation.recipe_detail.RecipeDetailScreen
import com.example.kmmfoodtofork.android.presentation.navigation.recipe_detail.RecipeDetailViewModel
import com.example.kmmfoodtofork.android.presentation.navigation.recipe_list.RecipeList
import com.example.kmmfoodtofork.android.presentation.navigation.recipe_list.RecipeListViewModel


@OptIn(ExperimentalMaterialApi::class, ExperimentalStdlibApi::class)
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.RecipeList.route) {
        composable(route = Screen.RecipeList.route) { _ ->
            val recipeListViewModel = hiltViewModel<RecipeListViewModel>()
            RecipeList(state = recipeListViewModel.state.value,
                onTriggerEvent = recipeListViewModel::onTriggerEvent,
                onClickRecipeListItem = { recipeId ->
                    navController.navigate(Screen.RecipeDetail.route + "/$recipeId")
                }
            )

        }

        composable(
            route = Screen.RecipeDetail.route + "/{recipeId}",
            arguments = listOf(navArgument("recipeId") {
                type = NavType.IntType
            })

        ) { _ ->
            val recipeDetailViewModel = hiltViewModel<RecipeDetailViewModel>()
            recipeDetailViewModel.state.value?.let {
                RecipeDetailScreen(state = it,
                    onTriggerEvent =recipeDetailViewModel::onTriggerEvent )
            }

        }
    }
}
