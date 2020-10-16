package com.warrior.marvelmovies.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.warrior.marvelmovies.MyApplication
import com.warrior.marvelmovies.R
import com.warrior.marvelmovies.viewmodel.MoviesViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MoviesViewModel

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
        (applicationContext as MyApplication).appComponent.inject(this)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(MoviesViewModel::class.java)
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
