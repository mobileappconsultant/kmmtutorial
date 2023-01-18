package com.example.kmmfoodtofork.android.di

import com.example.kmmfoodtofork.android.BaseApplication
import com.example.kmmfoodtofork.datasource.cache.DriverFactory
import com.example.kmmfoodtofork.datasource.cache.RecipeCache
import com.example.kmmfoodtofork.datasource.cache.RecipeCacheImpl
import com.example.kmmfoodtofork.datasource.cache.RecipeDatabase
import com.example.kmmfoodtofork.datasource.cache.RecipeDatabaseFactory
import com.example.kmmfoodtofork.domain.util.DatetimeUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {


    @Singleton
    @Provides
    fun provideRecipeDatabase(context: BaseApplication): RecipeDatabase {
        return RecipeDatabaseFactory(driverFactory = DriverFactory(context)).createDatabase()
    }


    @Singleton
    @Provides
    fun provideRecipeCache(recipeDatabase: RecipeDatabase): RecipeCache {
        return RecipeCacheImpl(recipeDatabase = recipeDatabase, datetimeUtil = DatetimeUtil())
    }
}
