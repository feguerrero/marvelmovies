package com.warrior.marvelmovies.presenter

import com.warrior.marvelmovies.view.DisplayableMovie
import com.warrior.marvelmovies.view.DisplayableMoviesWrapper
import kotlinx.coroutines.CoroutineScope

/**
 * Author: Felipe Guerrero
 */
interface ViewContract {

    interface View {

        fun showMovies(displayableMovies: List<DisplayableMovie>)

        fun hideLoading()

        fun showLoading()
    }

    interface Presenter : CoroutineScope {

        fun loadMovies()

        fun bind(view: View)

        fun unbind()

        fun getState(): DisplayableMoviesWrapper

        fun saveState(list: List<DisplayableMovie>)
    }
}
