package com.warrior.marvelmovies.di

import com.google.gson.GsonBuilder
import com.warrior.marvelmovies.model.remote.MoviesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Author: Felipe Guerrero
 */
@Module
@InstallIn(ActivityComponent::class)
object AppModule {

    @Provides
    fun provideMoviesService(): MoviesService {
        return Retrofit.Builder().apply {
            baseUrl(BASE_URL)
            addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        }.build().create(MoviesService::class.java)
    }
}

const val BASE_URL = "https://api.themoviedb.org/"
