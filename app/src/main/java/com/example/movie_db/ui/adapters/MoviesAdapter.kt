package com.example.movie_db.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movie_db.data.tmdb.PopularMovieBrief
import com.example.movie_db.databinding.ListItemMovieBinding
import com.example.movie_db.ui.MoviesFragmentDirections

class MoviesAdapter : ListAdapter<PopularMovieBrief, MoviesAdapter.MoviesViewHolder>(MovieDiff()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding =
            ListItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = getItem(position)
        (holder as MoviesViewHolder).bind(movie)
    }

    class MoviesViewHolder(private val binding: ListItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: PopularMovieBrief) {
            binding.apply {
                this.movieCard.setOnClickListener {
                    val directions =
                        MoviesFragmentDirections.actionMoviesFragmentToMovieDetailFragment(
                            backdropUrl = movie.backdropUrl,
                            movieId = movie.id
                        )
                    it.findNavController().navigate(directions)
                }
                this.movie = movie
                executePendingBindings() // TODO look into if this is still necessary
            }
        }
    }
}

private class MovieDiff : DiffUtil.ItemCallback<PopularMovieBrief>() {
    override fun areItemsTheSame(oldItem: PopularMovieBrief, newItem: PopularMovieBrief): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: PopularMovieBrief,
        newItem: PopularMovieBrief
    ): Boolean {
        return oldItem.title == newItem.title && oldItem.releaseDate == newItem.releaseDate
    }
}