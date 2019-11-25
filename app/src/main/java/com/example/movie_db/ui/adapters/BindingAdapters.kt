package com.example.movie_db.ui.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("genreList")
fun genreList(view: TextView, list: List<String>) {
    view.text = list.joinToString(separator = ", ")
}

@BindingAdapter("loadImage")
fun loadImage(view: ImageView, url: String) {
    if (url != null && url.length > 0) {
        Glide.with(view.context).load(url).into(view)
    }
}

@BindingAdapter("popularityText")
fun popularity(view: TextView, score: Double) {
    view.text = "$score/10"
}
