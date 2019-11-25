package com.example.movie_db.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie_db.data.Result
import com.example.movie_db.data.tmdb.PopularMovieBrief
import com.example.movie_db.data.tmdb.TMDBClient
import kotlinx.coroutines.launch

class MovieListViewModel : ViewModel() {
    private val TAG = "movieListViewModel"

    private val _movies: MutableLiveData<List<PopularMovieBrief>> = MutableLiveData()
    val movies: LiveData<List<PopularMovieBrief>>
        get() = _movies

    private val tmdbClient = TMDBClient()

    init {
        viewModelScope.launch { // Bound to Dispatcher.Main
            val result = tmdbClient.popularMovies()

            when (result) {
                is Result.Success -> {
                    // On Main thread so no need to use postValue
                    _movies.value = result.data.results
                }
                is Result.Error -> {
                    Log.e(TAG, "Error: ${result.exception}")
                }
            }
        }
    }
}