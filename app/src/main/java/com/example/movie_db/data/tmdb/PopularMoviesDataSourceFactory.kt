package com.example.movie_db.data.tmdb

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import kotlinx.coroutines.CoroutineScope

class PopularMoviesDataSourceFactory(private val scope: CoroutineScope): DataSource.Factory<Int, PopularMovieBrief>() {
    val sourceLiveData = MutableLiveData<PopularMoviesDataSource>()
    var latestSource: PopularMoviesDataSource? = null

    override fun create(): DataSource<Int, PopularMovieBrief> {
        latestSource = PopularMoviesDataSource(scope)
        sourceLiveData.postValue(latestSource)
        return latestSource as PopularMoviesDataSource
    }
}