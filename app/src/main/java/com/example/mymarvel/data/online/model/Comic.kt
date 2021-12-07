package com.example.mymarvel.data.online.model

data class Comic(
    val title: String,
    val description: String,
    val thumbnail: ImageData
) {
    data class ImageData(
        val path: String,
        val extension: String
    )
}