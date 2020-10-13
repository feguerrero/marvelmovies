package com.warrior.marvelmovies.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.warrior.marvelmovies.R
import kotlinx.android.synthetic.main.movie_viewholder.view.coverImageView
import kotlinx.android.synthetic.main.movie_viewholder.view.overviewTextView
import kotlinx.android.synthetic.main.movie_viewholder.view.titleTextView

/**
 * Author: Felipe Guerrero
 */
class MoviesAdapter(private val displayableMovies: List<DisplayableMovie>) :
    RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_viewholder, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(displayableMovies[position])
    }

    override fun getItemCount(): Int {
        return displayableMovies.size
    }
}

class MovieViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(displayableMovie: DisplayableMovie) {
        view.titleTextView.text = displayableMovie.title
        view.overviewTextView.text = displayableMovie.overview
        Glide.with(view.context)
            .load("$BASE_IMAGE_URL${displayableMovie.imagePath}")
            .centerCrop()
            .into(view.coverImageView)
    }
}

const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"
