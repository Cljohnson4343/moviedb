package com.example.movie_db.data.tmdb

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Client for The Movie DB API
 *
 * <a href="https://www.themoviedb.org/documentation/api">API Documentation</a>
 */
interface TMDBService {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String, @Query("page") page: Int): Response<PopularMovies>

    @GET("genre/movie/list")
    suspend fun getGenres(@Query("api_key") apiKey: String): Response<GenreResponse>

    @GET("configuration")
    suspend fun getConfiguration(@Query("api_key") apiKey: String): Response<Configuration>

    companion object {
        const val ENDPOINT = "https://api.themoviedb.org/3/"
    }
}