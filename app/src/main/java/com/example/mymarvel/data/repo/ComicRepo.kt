package com.example.mymarvel.data.repo

import com.example.mymarvel.AppConstants
import com.example.mymarvel.data.common.toMD5
import com.example.mymarvel.data.online.model.Comic
import com.example.mymarvel.data.online.retrofit.IComicService
import java.util.*

class ComicRepo(
    private val comicService: IComicService
) : IComicRepo {
    override suspend fun getComicById(comicId: String): Comic {
        val timestamp = Calendar.getInstance(TimeZone.getTimeZone("UTC")).timeInMillis
        val comics = comicService.getComicById(
            comicId = comicId,
            timestamp = timestamp,
            apiKey = AppConstants.API_KEY_PUBLIC,
            md5Hash = ("$timestamp${AppConstants.API_KEY_PRIVATE}${AppConstants.API_KEY_PUBLIC}").toMD5()
        ).data.results

        if (comics.isEmpty()) {
            throw Exception("No results found.")
        }

        return comics[0]
    }
}