package com.warrior.marvelmovies.model.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    @GET("1")
    suspend fun getMovies(
        @Query("page") page: Int = PAGE,
        @Query("api_key") apiKey: String = API_KEY
    ): ApiMovieList
}

const val PAGE = 1
const val API_KEY = "10a974073fcb07d013adef1e63f4f65b"
