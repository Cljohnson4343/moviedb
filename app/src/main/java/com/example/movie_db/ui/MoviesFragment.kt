package com.example.movie_db.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.movie_db.databinding.FragmentMoviesBinding
import com.example.movie_db.ui.adapters.MoviesAdapter
import com.example.movie_db.ui.viewmodels.MovieListViewModel

class MoviesFragment : Fragment() {
    private val viewModel: MovieListViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            ViewModelProvider.NewInstanceFactory()
        ).get(MovieListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMoviesBinding.inflate(inflater, container, false)

        val adapter = MoviesAdapter()
        binding.rvMoviesList.adapter = adapter
        viewModel.movies.observe(this, Observer {
            adapter.submitList(it)
        })

        return binding.root
    }
}