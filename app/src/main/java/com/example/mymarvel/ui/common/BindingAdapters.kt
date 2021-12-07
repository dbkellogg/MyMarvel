package com.example.mymarvel.ui.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadUrl", "fileType", requireAll = true)
fun loadUrlIntoImageView(view: ImageView, url: String?, extension: String?) {
    if (url.isNullOrBlank() || extension.isNullOrBlank()) {
        view.setImageDrawable(null)
        return
    }

    val formattedUrl = url.plus("/portrait_xlarge.").plus(extension)

    Glide.with(view)
        .load(formattedUrl)
        .into(view)
    //loading and fallback images are cool too
}