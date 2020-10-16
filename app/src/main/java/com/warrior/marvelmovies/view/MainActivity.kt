package com.warrior.marvelmovies.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.warrior.marvelmovies.MyApplication
import com.warrior.marvelmovies.R
import com.warrior.marvelmovies.presenter.ViewContract
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ViewContract.View {

    @Inject
    lateinit var presenter: ViewContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as MyApplication).appComponent.inject(this)
        setContentView(R.layout.activity_main)
        presenter.bind(this)
        setupRecyclerView()
        savedInstanceState?.getParcelable<DisplayableMoviesWrapper>(MOVIE_LIST_PARAM)?.list?.let {
            presenter.saveState(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unbind()
    }

    private fun setupRecyclerView() {
        moviesRecyclerView.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.loadMovies()
    }

    override fun showMovies(displayableMovies: List<DisplayableMovie>) {
        moviesRecyclerView.adapter = MoviesAdapter(displayableMovies)
    }

    override fun showLoading() {
        loadingProgressBar.visibility = View.VISIBLE
        moviesRecyclerView.visibility = View.GONE
    }

    override fun hideLoading() {
        loadingProgressBar.visibility = View.GONE
        moviesRecyclerView.visibility = View.VISIBLE
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val movieListWrapper = presenter.getState()
        if (movieListWrapper.list != null) {
            outState.putParcelable(MOVIE_LIST_PARAM, movieListWrapper)
        }
    }
}

const val MOVIE_LIST_PARAM = "MOVIE_LIST_PARAM"
