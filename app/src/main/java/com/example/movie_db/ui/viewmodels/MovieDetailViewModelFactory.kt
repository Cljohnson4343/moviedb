package com.example.movie_db.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Factory for creating a [MovieDetailViewModel] using a constructor that takes
 * an Id for the current [Movie]
 */
class MovieDetailViewModelFactory(private val movieId: Int, private val backdropUrl: String) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieDetailViewModel(movieId = movieId, backdropUrl = backdropUrl) as T
    }
}