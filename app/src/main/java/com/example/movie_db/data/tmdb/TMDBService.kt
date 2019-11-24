package com.example.movie_db.data.tmdb

import retrofit2.http.GET

/**
 * Client for The Movie DB API
 *
 * <a href="https://www.themoviedb.org/documentation/api">API Documentation</a>
 */
interface TMDBService {

    companion object {
        const val ENDPOINT = "https://api.themoviedb.org/3/"
    }
}