package com.example.kmmfoodtofork.android.di

import android.content.Context
import com.example.kmmfoodtofork.android.BaseApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApplicationContext(@ApplicationContext app: Context): BaseApplication {
        return app as BaseApplication
    }
}
