package com.warrior.marvelmovies.presenter

import com.warrior.marvelmovies.model.business.GetMoviesUseCase
import com.warrior.marvelmovies.view.DisplayableMovie
import com.warrior.marvelmovies.view.DisplayableMoviesWrapper
import com.warrior.marvelmovies.view.mapToDisplayableMovie
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Author: Felipe Guerrero
 */
class MoviesPresenter(
    override val coroutineContext: CoroutineContext,
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewContract.Presenter {

    private var view: ViewContract.View? = null
    private var displayableMovies: List<DisplayableMovie>? = null


    override fun bind(view: ViewContract.View) {
        this.view = view
    }

    override fun unbind() {
        coroutineContext.cancel()
        view = null
    }

    override fun loadMovies() {
        if (displayableMovies != null) {
            showMovies(displayableMovies!!)
        } else {
            callGetMoviesUseCase()
        }
    }

    private fun showMovies(movies: List<DisplayableMovie>) {
        view?.showMovies(movies)
        view?.hideLoading()
    }

    private fun callGetMoviesUseCase() {
        launch {
            try {
                view?.showLoading()
                displayableMovies = withContext(Dispatchers.IO + Job()) {
                    getMoviesUseCase.perform().map {
                        it.mapToDisplayableMovie()
                    }
                }
                showMovies(displayableMovies!!)
            } catch (e: Exception) {
                view?.hideLoading()
            }
        }
    }

    override fun getState(): DisplayableMoviesWrapper {
        return DisplayableMoviesWrapper(
            displayableMovies
        )
    }

    override fun saveState(list: List<DisplayableMovie>) {
        this.displayableMovies = list
        view?.hideLoading()
    }
}
