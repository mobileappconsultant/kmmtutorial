package com.example.kmmfoodtofork.presentation.recipe_list

import com.codingwithmitch.food2forkkmm.presentation.recipe_list.FoodCategory

sealed class RecipeListEvents {

    object LoadRecipes: RecipeListEvents()

    object NewSearch: RecipeListEvents()

    object NextPage: RecipeListEvents()

    data class OnUpdateQuery(val query: String): RecipeListEvents()

    data class OnSelectCategory(val category: FoodCategory): RecipeListEvents()

    object OnRemoveHeadMessageFromQueue: RecipeListEvents()

}
