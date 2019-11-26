package com.example.movie_db.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.movie_db.databinding.FragmentMovieDetailBinding
import com.example.movie_db.ui.viewmodels.MovieDetailViewModel
import com.example.movie_db.ui.viewmodels.MovieDetailViewModelFactory

class MovieDetailFragment : Fragment() {
    private val args: MovieDetailFragmentArgs by navArgs()

    private val TAG = "movieDetailFragment"

    private val viewModel: MovieDetailViewModel by lazy {
        ViewModelProvider(
            this,
            MovieDetailViewModelFactory(
                movieId = args.movieId,
                backdropUrl = args.backdropUrl,
                posterUrl = args.posterUrl
            )
        ).get(MovieDetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMovieDetailBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@MovieDetailFragment.viewModel
            executePendingBindings()
        }

        return binding.root
    }

}