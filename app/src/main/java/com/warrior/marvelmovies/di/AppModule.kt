package com.warrior.marvelmovies.di

import com.google.gson.GsonBuilder
import com.warrior.marvelmovies.model.remote.MoviesService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Author: Felipe Guerrero
 */
@Module
class AppModule {

    @Provides
    fun provideMoviesService(): MoviesService {
        return Retrofit.Builder().apply {
            baseUrl(BASE_URL)
            addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        }.build().create(MoviesService::class.java)
    }
}

const val BASE_URL = "https://api.themoviedb.org/4/list/"
