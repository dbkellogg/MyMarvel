package com.example.mymarvel.data.online.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private const val URL_ROOT = "https://gateway.marvel.com/v1/public/"

    private val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client : OkHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(interceptor)
    }.build()

    private val builder: Retrofit
    get() = Retrofit.Builder()
        .baseUrl(URL_ROOT)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val comicService: IComicService = builder.create(IComicService::class.java)
}