package com.example.movie_db

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.movie_db.data.Result
import com.example.movie_db.data.tmdb.TMDBClient
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    private val TAG = "mainActivityTag"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val client = TMDBClient()

        runBlocking {
            val result = client.popularMovies()
            when (result) {
                is Result.Success -> {
                    Log.d(TAG, "Success: ${result.data}")
                }
                is Result.Error -> {
                    Log.e(TAG, "Error: ${result.exception}")
                }
            }
        }
    }
}
