package com.example.kmmfoodtofork.di

import com.example.kmmfoodtofork.datasource.cache.DriverFactory
import com.example.kmmfoodtofork.datasource.cache.RecipeCache
import com.example.kmmfoodtofork.datasource.cache.RecipeCacheImpl
import com.example.kmmfoodtofork.datasource.cache.RecipeDatabase
import com.example.kmmfoodtofork.datasource.cache.RecipeDatabaseFactory
import com.example.kmmfoodtofork.domain.util.DatetimeUtil

class CacheModule {
    private val driverFactory: DriverFactory by lazy { DriverFactory() }
    private val recipeDatabase: RecipeDatabase by lazy {
        RecipeDatabaseFactory(driverFactory = driverFactory).createDatabase()
    }
    val recipeCache: RecipeCache by lazy {
        RecipeCacheImpl(
            recipeDatabase = recipeDatabase,
            datetimeUtil = DatetimeUtil()
        )
    }
}
