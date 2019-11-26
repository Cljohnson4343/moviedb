package com.example.movie_db.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.movie_db.data.tmdb.PopularMovieBrief
import com.example.movie_db.data.tmdb.PopularMoviesDataSourceFactory

class MovieListViewModel : ViewModel() {
    private val TAG = "movieListViewModel"

    var movies: LiveData<PagedList<PopularMovieBrief>>

    init {
        movies = LivePagedListBuilder<Int, PopularMovieBrief>(
            PopularMoviesDataSourceFactory(viewModelScope),
            PagedList.Config.Builder()
                .setPageSize(20)
                .setEnablePlaceholders(false)
                .build()
        ).build()
    }

    /*
    private val genreMap: HashMap<Int, String> = HashMap()

    private val posterSize = "original"

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
        _movies.value = _movies.value?.map {
            it.posterUrl = "$base${this.posterSize}${it.posterPath}"
            it.backdropUrl = "${base}original${it.backdropPath}"
            it
        }
    }

     */
}