package com.example.movie_db.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie_db.data.Result
import com.example.movie_db.data.tmdb.PopularMovieBrief
import com.example.movie_db.data.tmdb.TMDBClient
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MovieListViewModel : ViewModel() {
    private val TAG = "movieListViewModel"

    private val _movies: MutableLiveData<List<PopularMovieBrief>> = MutableLiveData()
    val movies: LiveData<List<PopularMovieBrief>>
        get() = _movies

    private val genreMap: HashMap<Int, String> = HashMap()

    private val posterSize = "original"

    private val tmdbClient = TMDBClient()

    init {
        //
        // TODO clean this block up.
        //

        viewModelScope.launch { // Bound to Dispatcher.Main
            val configDeferred = async { tmdbClient.configuration() }
            val genreDeferred = async { tmdbClient.genres() }
            val moviesResult = async { tmdbClient.popularMovies() }.await()

            when (moviesResult) {
                is Result.Success -> {
                    // On Main thread so no need to use postValue
                    _movies.value = moviesResult.data.results
                }
                is Result.Error -> {
                    Log.e(TAG, "Error: ${moviesResult.exception}")
                }
            }

            val genreResult = genreDeferred.await()
            when (genreResult) {
                is Result.Success -> {
                    genreResult.data.forEach {
                        genreMap.put(it.id, it.name)
                    }
                    addGenres()
                }
                is Result.Error -> {
                    Log.e(TAG, "Error: ${genreResult.exception}")
                }
            }

            val configResult = configDeferred.await()
            when (configResult) {
                is Result.Success -> {
                    addUrls(configResult.data.images.secureBaseUrl)
                }
                is Result.Error -> {
                    Log.e(TAG, "Error: ${configResult.exception}")
                }
            }
        }
    }

    private fun addGenres() {
        _movies.value = _movies.value?.map {
            it.genres = getGenres(it.genreIds)
            it
        }
    }
    private fun getGenres(list: List<Int>): List<String> {
        return list.map {
            genreMap.get(it) ?: ""
        }
    }

    private fun addUrls(base: String) {
        _movies.value = _movies.value?.map{
            it.posterUrl = "$base${this.posterSize}${it.posterPath}"
            it.backdropUrl = "${base}original${it.backdropPath}"
            it
        }
    }
}