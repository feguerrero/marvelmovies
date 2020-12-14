package com.warrior.marvelmovies.view

import com.warrior.marvelmovies.model.remote.Movie

/**
 * Author: Felipe Guerrero
 */
data class DisplayableMovie(
    val title: String,
    val overview: String,
    val imagePath: String
)

fun Movie.mapToDisplayableMovie(): DisplayableMovie {
    return DisplayableMovie(
        title,
        overview,
        image
    )
}
