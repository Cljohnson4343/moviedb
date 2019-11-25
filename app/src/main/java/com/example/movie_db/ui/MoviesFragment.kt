package com.example.movie_db.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.movie_db.data.Result
import com.example.movie_db.data.tmdb.TMDBClient
import com.example.movie_db.databinding.FragmentMoviesBinding
import com.example.movie_db.ui.adapters.MoviesAdapter
import kotlinx.coroutines.runBlocking

class MoviesFragment : Fragment() {
    private val TAG = "moviesFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMoviesBinding.inflate(inflater, container, false)
        binding.rvMoviesList.adapter = MoviesAdapter()

        val client = TMDBClient()

        runBlocking {
            val result = client.popularMovies()
            when (result) {
                is Result.Success -> {
                    (binding.rvMoviesList.adapter as MoviesAdapter).submitList(result.data.results)
                }
                is Result.Error -> {
                    Log.e(TAG, "Error: ${result.exception}")
                }
            }
        }

        return binding.root
    }
}