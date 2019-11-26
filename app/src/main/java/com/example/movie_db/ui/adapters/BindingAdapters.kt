package com.example.movie_db.ui.adapters

import android.graphics.drawable.Drawable
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.movie_db.data.tmdb.Genre

@BindingAdapter("genreList")
fun genreList(view: TextView, list: List<String>?) {
    if (list != null) {
        view.text = list.joinToString(separator = ", ")
    }
}

@BindingAdapter("loadImage")
fun loadImage(view: ImageView, url: String?) {
    if (url != null && url.length > 0) {
        Glide.with(view.context).load(url).into(view)
    }
}

@BindingAdapter("popularityText")
fun popularity(view: TextView, score: Double) {
    view.text = "$score/10"
}

@BindingAdapter("setBackdropImage")
fun setBackdropImage(view: FrameLayout, url: String?) {
    // TODO check to see if null check is necessary (i think generated code has null check)
    if (url != null && url.length > 0) {
        Glide.with(view.context)
            .load(url)
            .into(object : CustomTarget<Drawable>() {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?
                ) {
                    view.setBackground(resource)
                }

                override fun onLoadCleared(placeholder: Drawable?) {}
            })
    }
}

@BindingAdapter("releaseYear")
fun setReleaseYear(view: TextView, date: String?) {
    date?.let {
        view.text = it.take(4)
    }
}

@BindingAdapter("runtime")
fun setRuntime(view: TextView, mins: Int?) {
    mins?.let {
        val h = it / 60
        val m = it % 60
        view.text = "${h}h${m}min"
    }
}

@BindingAdapter("genreList")
fun setGenres(view: TextView, list: List<Genre>?) {
    list?.let {
        view.text = it.map{ genre ->
            genre.name ?: ""
        }.joinToString(", ")
    }
}

