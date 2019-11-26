package com.example.movie_db.data.tmdb

import android.util.Log
import androidx.paging.PositionalDataSource
import com.example.movie_db.data.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class PopularMoviesDataSource(private val scope: CoroutineScope) :
    PositionalDataSource<PopularMovieBrief>() {
    private val TAG = "popularMoviesDataSource"

    override fun loadRange(
        params: LoadRangeParams,
        callback: LoadRangeCallback<PopularMovieBrief>
    ) {
        Log.d(
            TAG,
            "loadRange: (params.loadSize = ${params.loadSize}, params.startPosition = ${params.startPosition}"
        )

        // TODO refactor to remove duplication
        // TODO enable coroutinescope injection to allow accurate coroutine cancellation
        scope.launch {
            val page = (params.startPosition / params.loadSize) + 1
            Log.d(TAG, "Requesting page $page")
            val result = async { TMDBClient.popularMovies(page) }.await()

            when (result) {
                is Result.Success -> {
                    Log.d(
                        TAG,
                        "Result: (page = ${result.data.page},totalPages = ${result.data.totalPages}, totalResults = ${result.data.totalResults}"
                    )
                    callback.onResult(result.data.results.map { movieBrief ->
                        movieBrief?.let {
                            // TODO Refactor to get rid of hard-coded urls
                            it.posterUrl = "https://image.tmdb.org/t/p/original/${it.posterPath}"
                            it.backdropUrl =
                                "https://image.tmdb.org/t/p/original/${it.backdropPath}"
                            it
                        }
                    })
                }
                is Result.Error -> {
                    Log.e(TAG, "Error: ${result.exception}")
                }
            }
        }
    }

    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<PopularMovieBrief>
    ) {
        Log.d(
            TAG,
            "loadInitial: (params.pageSize = ${params.pageSize}, params.requestedLoadSize = ${params.requestedLoadSize}"
        )
        scope.launch {
            val result = async { TMDBClient.popularMovies() }.await()

            when (result) {
                is Result.Success -> {
                    Log.d(
                        TAG,
                        "Result: (page = ${result.data.page},totalPages = ${result.data.totalPages}, totalResults = ${result.data.totalResults}"
                    )
                    callback.onResult(
                        result.data.results.map { movieBrief ->
                            movieBrief?.let {
                                // TODO Refactor to get rid of hard-coded urls
                                it.posterUrl =
                                    "https://image.tmdb.org/t/p/original/${it.posterPath}"
                                it.backdropUrl =
                                    "https://image.tmdb.org/t/p/original/${it.backdropPath}"
                                it
                            }
                        },
                        0,
                        result.data.totalPages * result.data.results.size
                    )
                }
                is Result.Error -> {
                    Log.e(TAG, "Error: ${result.exception}")
                }
            }
        }
    }
}