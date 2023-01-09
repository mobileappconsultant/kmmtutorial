package com.example.kmmfoodtofork.android.di

import com.example.kmmfoodtofork.datasourc.network.RecipeService
import com.example.kmmfoodtofork.interactors.recipe_detail.GetRecipe
import com.example.kmmfoodtofork.interactors.recipe_list.SearchRecipe
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InteractorsModule {

    @Provides
    @Singleton
    fun providesSearch(recipeService: RecipeService): SearchRecipe =
        SearchRecipe(recipeService)

    @Provides
    @Singleton
    fun providesGetRecipe(recipeService: RecipeService): GetRecipe =
        GetRecipe(recipeService)
}
