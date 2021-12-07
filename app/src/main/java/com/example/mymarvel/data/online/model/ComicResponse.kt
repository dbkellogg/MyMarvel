package com.example.mymarvel.data.online.model

data class ComicResponse(
    val data: ResponseData
) {
    data class ResponseData(
        val results: List<Comic>
    )
}
