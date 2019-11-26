package com.example.movie_db.data.tmdb

import android.util.Log
import androidx.paging.PositionalDataSource
import com.example.movie_db.data.Result
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class PopularMoviesDataSource : PositionalDataSource<PopularMovieBrief>() {
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
        GlobalScope.launch {
            val moviesResult = async { TMDBClient.popularMovies(params.startPosition) }.await()

            when (moviesResult) {
                is Result.Success -> {
                    Log.d(TAG, "Result: ${moviesResult.data}")
                    callback.onResult(moviesResult.data.results.map { movieBrief ->
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
                    Log.e(TAG, "Error: ${moviesResult.exception}")
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
            "loadRange: (params.pageSize = ${params.pageSize}, params.requestedLoadSize = ${params.requestedLoadSize}"
        )
        GlobalScope.launch {
            val result = async { TMDBClient.popularMovies() }.await()

            when (result) {
                is Result.Success -> {
                    Log.d(TAG, "Result: ${result.data}")
                    callback.onResult(
                        result.data.results.map{ movieBrief ->
                            movieBrief?.let {
                                // TODO Refactor to get rid of hard-coded urls
                                it.posterUrl =
                                    "https://image.tmdb.org/t/p/original/${it.posterPath}"
                                it.backdropUrl =
                                    "https://image.tmdb.org/t/p/original/${it.backdropPath}"
                                it
                            }
                        },
                        result.data.page,
                        result.data.totalPages
                    )
                }
                is Result.Error -> {
                    Log.e(TAG, "Error: ${result.exception}")
                }
            }
        }
    }
}