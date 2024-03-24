package com.example.wallcraft.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.wallcraft.data.repository.SearchRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SearchRepository,
    state: SavedStateHandle
) : ViewModel() {







    private val currentData = state.getLiveData(CURRENT_QUERY, DEFAULT_QUERY)

    val searchPhotos= currentData.switchMap {
        repository.searchPhoto(it).cachedIn(viewModelScope)
  }

    fun getSearchPhotoId(id: String) {
        currentData.postValue(id)
    }

    companion object {
        private const val CURRENT_QUERY = "current_query_search"
        private const val DEFAULT_QUERY = "search"
    }

}