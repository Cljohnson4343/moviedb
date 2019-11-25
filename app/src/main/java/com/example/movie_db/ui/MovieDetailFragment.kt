package com.example.movie_db.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs

class MovieDetailFragment: Fragment() {

    private val TAG = "movieDetailFragment"

    private val args: MovieDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "id ${args.movieId}")
        Log.d(TAG, "id ${args.backdropUrl}")
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}