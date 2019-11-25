package com.example.movie_db.ui.adapters

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("genreList")
fun genreList(view: TextView, list: List<String>) {
    view.text = list.joinToString(separator = ", ")
}