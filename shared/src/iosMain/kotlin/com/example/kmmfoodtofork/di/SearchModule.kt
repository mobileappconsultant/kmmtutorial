package com.example.kmmfoodtofork.di

import com.example.kmmfoodtofork.interactors.recipe_list.SearchRecipe

class SearchModule(private val networkModule: NetworkModule, private val cacheModule: CacheModule) {
    val searchRecipes: SearchRecipe by lazy {
        SearchRecipe(
            networkModule.recipeService,
            cacheModule.recipeCache
        )
    }
}
