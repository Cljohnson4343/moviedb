package com.example.movie_db.data.tmdb

import retrofit2.Retrofit

/**
 * Data source class for the TMDB API
 */
class TMDBClient() {
    private val service: TMDBService by lazy {
        Retrofit.Builder()
            .baseUrl(TMDBService.ENDPOINT)
            .build()
            .create(TMDBService::class.java)
    }
}