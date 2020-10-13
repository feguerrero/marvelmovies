package com.warrior.marvelmovies.model.remote

import android.os.Parcelable

/**
 * Author: Felipe Guerrero
 */
data class ApiMovieList(
    val results: List<ApiMovie>
)

data class ApiMovie(
    val title: String,
    val overview: String,
    val backdrop_path: String
)
