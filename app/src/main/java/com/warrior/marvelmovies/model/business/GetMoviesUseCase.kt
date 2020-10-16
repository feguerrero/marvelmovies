package com.warrior.marvelmovies.model.business

import com.warrior.marvelmovies.model.remote.ApiMovieList
import com.warrior.marvelmovies.model.remote.Movie
import com.warrior.marvelmovies.view.DisplayableMovie
import kotlinx.coroutines.delay
import javax.inject.Inject

/**
 * Author: Felipe Guerrero
 */
class GetMoviesUseCase @Inject constructor(
    private val repository: MoviesRepository
) {

    suspend fun perform(): List<Movie> {
        delay(5_000)
        return repository.getMovies().toMovieList()
    }
}

fun ApiMovieList.toMovieList(): List<Movie> {
    return this.results.map {
        Movie(
            title = it.title,
            overview = it.overview,
            image = it.backdrop_path
        )
    }
}
