package com.example.movie_db.ui.adapters

import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

@BindingAdapter("genreList")
fun genreList(view: TextView, list: List<String>) {
    view.text = list.joinToString(separator = ", ")
}

@BindingAdapter("loadImage")
fun loadImage(view: ImageView, url: String) {
    // TODO check to see if null check is necessary (i think generated code has null check)
    if (url != null && url.length > 0) {
        Glide.with(view.context).load(url).into(view)
    }
}

@BindingAdapter("popularityText")
fun popularity(view: TextView, score: Double) {
    view.text = "$score/10"
}

@BindingAdapter("setBackdropImage")
fun setBackdropImage(view: ConstraintLayout, url: String) {
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
