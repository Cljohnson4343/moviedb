package com.example.movie_db

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.movie_db.data.Result
import com.example.movie_db.data.tmdb.TMDBClient
import com.example.movie_db.ui.adapters.MoviesAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    private val TAG = "mainActivityTag"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO replace kotlin synthetic calls
        rv_movies_list.adapter = MoviesAdapter()

        val client = TMDBClient()

        runBlocking {
            val result = client.popularMovies()
            when (result) {
                is Result.Success -> {
                    (rv_movies_list.adapter as MoviesAdapter).submitList(result.data.results)
                }
                is Result.Error -> {
                    Log.e(TAG, "Error: ${result.exception}")
                }
            }
        }
    }
}
