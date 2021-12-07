package com.example.mymarvel.data.online.retrofit

import com.example.mymarvel.data.online.model.ComicResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IComicService {
    @GET("comics/{comicId}")
    suspend fun getComicById(
        @Path("comicId") comicId: String,
        @Query("ts") timestamp: Long,
        @Query("apikey") apiKey: String,
        @Query("hash") md5Hash: String
    ): ComicResponse
}