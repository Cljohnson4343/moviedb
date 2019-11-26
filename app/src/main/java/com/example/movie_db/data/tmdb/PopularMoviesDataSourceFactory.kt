package com.example.movie_db.data.tmdb

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource

class PopularMoviesDataSourceFactory: DataSource.Factory<Int, PopularMovieBrief>() {
    val sourceLiveData = MutableLiveData<PopularMoviesDataSource>()
    var latestSource: PopularMoviesDataSource? = null

    override fun create(): DataSource<Int, PopularMovieBrief> {
        latestSource = PopularMoviesDataSource()
        sourceLiveData.postValue(latestSource)
        return latestSource as PopularMoviesDataSource
    }
}