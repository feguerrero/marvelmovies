package com.warrior.marvelmovies.view

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Author: Felipe Guerrero
 */
@Parcelize
data class DisplayableMovie(
    val title: String,
    val overview: String,
    val imagePath: String
) : Parcelable

@Parcelize
data class DisplayableMoviesWrapper(
    val list: List<DisplayableMovie>?
) : Parcelable
