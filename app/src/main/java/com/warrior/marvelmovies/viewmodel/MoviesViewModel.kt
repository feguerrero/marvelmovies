package com.warrior.marvelmovies.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.warrior.marvelmovies.model.business.GetMoviesUseCase
import com.warrior.marvelmovies.view.DisplayableMovie
import com.warrior.marvelmovies.view.mapToDisplayableMovie
import kotlinx.coroutines.launch

/**
 * @author Felipe E Guerrero
 */
class MoviesViewModel @ViewModelInject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    val displayableMovies = MutableLiveData<List<DisplayableMovie>>().apply {
        value = emptyList()
    }

    val isLoading = MutableLiveData<Boolean>().apply {
        value = true
    }

    init {
        loadMovies()
    }

    private fun loadMovies() {
        viewModelScope.launch {
            try {
                displayableMovies.value = getMoviesUseCase.perform().map {
                    it.mapToDisplayableMovie()
                }
                isLoading.value = false
            } catch (e: Exception) {
                isLoading.value = false
            }
        }
    }
}
