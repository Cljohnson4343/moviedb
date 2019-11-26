package com.example.movie_db.data.tmdb

import android.util.Log
import androidx.paging.PositionalDataSource
import com.example.movie_db.data.Result
import kotlinx.coroutines.CoroutineScope
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

        scope.launch {
            val page = (params.startPosition / params.loadSize) + 1
            Log.d(TAG, "Requesting page $page")
            val result = async { TMDBClient.popularMovies(page) }.await()
            result.handleResult(Callback.Range(callback))
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
            result.handleResult(Callback.Initial(callback))
        }
    }

    private fun Result<PopularMovies>.handleResult(callback: Callback) {
        when (this) {
            is Result.Success -> {
                val results = this.data.results
                val totalsCount = this.data.totalResults * results.size
                Log.d(
                    TAG,
                    "Result: (page = ${this.data.page},totalPages = ${this.data.totalPages}, totalResults = ${this.data.totalResults}"
                )
                when (callback) {
                    is Callback.Initial -> {
                        callback.callback.onResult(results.map { movieBrief ->
                            movieBrief?.let {
                                // TODO Refactor to get rid of hard-coded urls
                                it.posterUrl =
                                    "https://image.tmdb.org/t/p/original/${it.posterPath}"
                                it.backdropUrl =
                                    "https://image.tmdb.org/t/p/original/${it.backdropPath}"
                                it
                            }
                        }, 0, totalsCount)
                    }
                    is Callback.Range -> {
                        callback.callback.onResult(results.map { movieBrief ->
                            movieBrief?.let {
                                // TODO Refactor to get rid of hard-coded urls
                                it.posterUrl =
                                    "https://image.tmdb.org/t/p/original/${it.posterPath}"
                                it.backdropUrl =
                                    "https://image.tmdb.org/t/p/original/${it.backdropPath}"
                                it
                            }
                        })
                    }
                }
            }
            is Result.Error -> {
                Log.e(TAG, "Error: ${this.exception}")
            }
        }
    }
}

private sealed class Callback {
    data class Range(val callback: PositionalDataSource.LoadRangeCallback<PopularMovieBrief>) :
        Callback()

    data class Initial(val callback: PositionalDataSource.LoadInitialCallback<PopularMovieBrief>) :
        Callback()
}
