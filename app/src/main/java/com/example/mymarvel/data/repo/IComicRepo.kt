package com.example.mymarvel.data.repo

import com.example.mymarvel.data.online.model.Comic
import kotlin.jvm.Throws

interface IComicRepo {
    @Throws(Exception::class)
    suspend fun getComicById(comicId: String): Comic
}