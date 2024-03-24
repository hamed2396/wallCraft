package com.example.wallcraft.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crazylegend.kotlinextensions.force
import com.example.wallcraft.data.db.entity.FavoriteEntity
import com.example.wallcraft.data.models.detail.ResponseDetail
import com.example.wallcraft.data.repository.DetailRepository
import com.example.wallcraft.utils.Constants
import com.example.wallcraft.utils.network.NetworkResponse
import com.example.wallcraft.utils.network.NetworkStatus
import com.example.wallcraft.utils.network.NetworkStatus.Error

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: DetailRepository,
    private val networkResponse: NetworkResponse
) : ViewModel() {


    private val _detailPhoto = MutableLiveData<NetworkStatus<ResponseDetail>>()
    val detailPhoto get() = _detailPhoto.force<LiveData<NetworkStatus<ResponseDetail>>>()

    fun getCategory(id: String) = viewModelScope.launch {
        _detailPhoto.postValue(NetworkStatus.Loading())
        try {
            val response = repository.getDetailPhoto(id)
            _detailPhoto.postValue(networkResponse.handleResponse(response))
        } catch (_: Exception) {
            _detailPhoto.postValue(Error(Constants.CHECK_CONNECTION))
        }

    }

    //db

    fun insertFavorite(entity: FavoriteEntity) = viewModelScope.launch {

        repository.insertFavorite(entity)
    }


    fun deleteFavorite(entity: FavoriteEntity) = viewModelScope.launch {
        repository.deleteFavorite(entity)
    }

    fun isFavorite(id: String) = repository.isFavorite(id)
    fun findTableId(id: String) = repository.findTableId(id)
}