package com.example.kmmfoodtofork.di

import com.example.kmmfoodtofork.interactors.recipe_detail.GetRecipe

class GetRecipeModule(private val cacheModule: CacheModule) {
    val getRecipe: GetRecipe by lazy {
        GetRecipe(recipeCache = cacheModule.recipeCache)
    }
}
