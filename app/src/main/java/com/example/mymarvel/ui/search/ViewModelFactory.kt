package com.example.mymarvel.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mymarvel.data.online.retrofit.ServiceBuilder
import com.example.mymarvel.data.repo.ComicRepo
import com.example.mymarvel.data.repo.IComicRepo

class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass == SearchViewModel::class.java) {
            return SearchViewModel(getComicRepo()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

    private fun getComicRepo(): IComicRepo = ComicRepo(ServiceBuilder.comicService) //fixme
}