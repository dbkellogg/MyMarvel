package com.example.mymarvel.ui.search

import androidx.lifecycle.*
import com.example.mymarvel.data.online.model.Comic
import com.example.mymarvel.data.repo.IComicRepo
import kotlinx.coroutines.launch

class SearchViewModel(
    private val comicRepo: IComicRepo
) : ViewModel() {
    private val mutableEvents = MutableLiveData<Event>() //not perfect - doesn't clear events after consumption
    val events: LiveData<Event>
    get() = mutableEvents

    sealed class Event {
        data class LoadingStatus(val isLoading: Boolean): Event()
        data class SearchError(val errorMsg: String): Event()
    }

    private val mutableViewData: MutableLiveData<Comic?> = MutableLiveData<Comic?>()
    val viewData: LiveData<Comic?>
    get() = mutableViewData

    fun search(query: String?) {
        if (query.isNullOrBlank()) {
            mutableViewData.value = null
            return
        }

        viewModelScope.launch {
            mutableEvents.postValue(Event.LoadingStatus(true))

            try {
                val comic = comicRepo.getComicById(comicId = query)
                mutableEvents.postValue(Event.LoadingStatus(false))
                mutableViewData.postValue(comic)
            } catch (exception: Exception) {
                mutableViewData.postValue(null)
                mutableEvents.postValue(Event.SearchError(exception.message ?: "Error occurred!"))
            }
        }
    }
}