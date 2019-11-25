package com.example.movie_db.data.tmdb

import com.example.movie_db.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.movie_db.data.Result
import com.example.movie_db.data.apiCall
import java.io.IOException

/**
 * Data source class for the TMDB API
 */
class TMDBClient() {
    // TODO inject this dependency
    private val service: TMDBService by lazy {
        Retrofit.Builder()
            .baseUrl(TMDBService.ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TMDBService::class.java)
    }

    suspend fun popularMovies(page: Int = 1): Result<PopularMovies> = apiCall(
        call = { loadPopularMovies(page) },
        errorMessage = "Error loading movies"
    )

    private suspend fun loadPopularMovies(page: Int = 1): Result<PopularMovies> {
        val response = service.getPopularMovies(
            apiKey = BuildConfig.MOVIE_DB_KEY,
            page = page
        )
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                return Result.Success(body)
            }
        }
        return Result.Error(
            IOException("Error loading popular movies ${response.code()} ${response.message()}")
        )
    }

    suspend fun genres(): Result<List<Genre>> = apiCall(
        call = { loadGenres() },
        errorMessage = "Error loading genres"
    )

    private suspend fun loadGenres(): Result<List<Genre>> {
        val response = service.getGenres(BuildConfig.MOVIE_DB_KEY)
        if (response.isSuccessful){
            val body = response.body()
            if (body != null) {
                return Result.Success(body.genres)
            }
        }
        return Result.Error(
            IOException("Error loading genres ${response.code()} ${response.message()}")
        )
    }
}