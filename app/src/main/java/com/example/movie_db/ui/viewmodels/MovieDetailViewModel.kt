package com.example.movie_db.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie_db.data.Result
import com.example.movie_db.data.tmdb.Movie
import com.example.movie_db.data.tmdb.TMDBClient
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val movieId: Int, val backdropUrl: String) : ViewModel() {

    private val TAG = "movieDetailViewModel"

    private val _movie: MutableLiveData<Movie> = MutableLiveData()
    val movie: LiveData<Movie>
        get() = _movie

    init {
        viewModelScope.launch {
            val result = TMDBClient.movie(movieId)
            when (result) {
                is Result.Success -> {
                    _movie.value = result.data
                    Log.d(TAG, "Result ${result.data.title}")
                }
                is Result.Error -> {
                    Log.d(TAG, "Error ${result.exception}")
                }
            }
        }
    }
}