package com.example.kmmfoodtofork.android.di

import com.example.kmmfoodtofork.datasourc.network.KtorClientFactory
import com.example.kmmfoodtofork.datasourc.network.RecipeService
import com.example.kmmfoodtofork.datasourc.network.RecipeServiceImpl
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
