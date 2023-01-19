package com.example.kmmfoodtofork.presentation.recipe_list

import com.codingwithmitch.food2forkkmm.presentation.recipe_list.FoodCategory
import com.example.kmmfoodtofork.domain.model.GenericMessageInfo
import com.example.kmmfoodtofork.domain.model.Recipe
import com.example.kmmfoodtofork.domain.util.Queue

data class RecipeListState(
    val isLoading: Boolean = false,
    val page: Int = 1,
    val query: String = "",
    val selectedCategory: FoodCategory? = null,
    val recipes: List<Recipe> = listOf(),
    val queue: Queue<GenericMessageInfo> = Queue(mutableListOf()), // messages to be displayed in ui
)
{
    constructor():this(isLoading=false,page=1,query="",selectedCategory=null,recipes= listOf(),queue= Queue(
        mutableListOf()
    )
    )
}
