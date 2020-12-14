package com.warrior.marvelmovies.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.warrior.marvelmovies.R
import com.warrior.marvelmovies.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MoviesViewModel by viewModels()

    private val displayableMoviesObserver = Observer<List<DisplayableMovie>> {
        showMovies(it)
    }

    private val isViewLoadingObserver = Observer<Boolean> {
        if (it) {
            showLoading()
        } else {
            hideLoading()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel.displayableMovies.observe(this, displayableMoviesObserver)
        viewModel.isLoading.observe(this, isViewLoadingObserver)
    }

    private fun setupRecyclerView() {
        moviesRecyclerView.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun showMovies(displayableMovies: List<DisplayableMovie>) {
        moviesRecyclerView.adapter = MoviesAdapter(displayableMovies)
    }

    private fun showLoading() {
        loadingProgressBar.visibility = View.VISIBLE
        moviesRecyclerView.visibility = View.GONE
    }

    private fun hideLoading() {
        loadingProgressBar.visibility = View.GONE
        moviesRecyclerView.visibility = View.VISIBLE
    }
}
