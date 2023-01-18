package com.example.kmmfoodtofork.android.di

import com.example.kmmfoodtofork.datasource.network.KtorClientFactory
import com.example.kmmfoodtofork.datasource.network.RecipeService
import com.example.kmmfoodtofork.datasource.network.RecipeServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun httpClient(): HttpClient {
        return KtorClientFactory().build()
    }

    @Singleton
    @Provides
    fun recipeService(httpClient: HttpClient): RecipeService {
        return RecipeServiceImpl(httpClient, "")
    }
}
