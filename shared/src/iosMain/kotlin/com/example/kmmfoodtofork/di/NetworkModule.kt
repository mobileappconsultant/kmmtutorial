package com.example.kmmfoodtofork.di

import com.example.kmmfoodtofork.datasource.network.KtorClientFactory
import com.example.kmmfoodtofork.datasource.network.RecipeService
import com.example.kmmfoodtofork.datasource.network.RecipeServiceImpl

class NetworkModule {
    val recipeService: RecipeService by lazy {
        RecipeServiceImpl(httpclient = KtorClientFactory().build(), baseUrl = "")
    }
}
