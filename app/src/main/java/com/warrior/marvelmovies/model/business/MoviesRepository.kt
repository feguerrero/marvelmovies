package com.warrior.marvelmovies.model.business

import com.warrior.marvelmovies.model.remote.ApiMovieList
import com.warrior.marvelmovies.model.remote.MoviesService
import javax.inject.Inject

/**
 * Author: Felipe Guerrero
 */
class MoviesRepository @Inject constructor(
    private val service: MoviesService
) {

    suspend fun getMovies(): ApiMovieList {
        return service.getMovies()
    }
}
