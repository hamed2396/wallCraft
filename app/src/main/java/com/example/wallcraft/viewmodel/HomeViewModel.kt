package com.example.wallcraft.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.crazylegend.kotlinextensions.force
import com.example.wallcraft.data.models.home.ResponseCategories
import com.example.wallcraft.data.models.home.ResponsePhotos
import com.example.wallcraft.data.repository.HomeRepository
import com.example.wallcraft.utils.Constants
import com.example.wallcraft.utils.Constants.DEFAULT_PHOTO_ID
import com.example.wallcraft.utils.network.NetworkResponse
import com.example.wallcraft.utils.network.NetworkStatus
import com.example.wallcraft.utils.network.NetworkStatus.Error

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository,
    private val networkResponse: NetworkResponse,
    state: SavedStateHandle
) : ViewModel() {
    init {
        viewModelScope.launch {
            delay(250)
            getCategory()
          getHomePhotoId(DEFAULT_PHOTO_ID)
        }


    }

    private val _categoryItems = MutableLiveData<NetworkStatus<ResponseCategories>>()
    val categoryItems get() = _categoryItems.force<LiveData<NetworkStatus<ResponseCategories>>>()

    fun getCategory() = viewModelScope.launch {
        _categoryItems.postValue(NetworkStatus.Loading())
        try {
            val response = repository.getCategory()
            _categoryItems.postValue(networkResponse.handleResponse(response))
        } catch (_: Exception) {
            _categoryItems.postValue(Error(Constants.CHECK_CONNECTION))
        }

    }

    private val currentData = state.getLiveData(CURRENT_QUERY, DEFAULT_QUERY)

    val homePhotos= currentData.switchMap {
        repository.homePhoto(it).cachedIn(viewModelScope)
  }

    fun getHomePhotoId(id: String) {
        currentData.postValue(id)
    }

    companion object {
        private const val CURRENT_QUERY = "current_query"
        private const val DEFAULT_QUERY = ""
    }

}