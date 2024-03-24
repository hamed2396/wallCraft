package com.example.wallcraft.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.crazylegend.kotlinextensions.force
import com.example.wallcraft.data.db.entity.FavoriteEntity
import com.example.wallcraft.data.repository.FavoriteRepository
import com.example.wallcraft.data.repository.SearchRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: FavoriteRepository,

) : ViewModel() {



    private val _getAllFavorite = MutableLiveData<List<FavoriteEntity>>()
    val getAllFavorite get() = _getAllFavorite.force<LiveData<List<FavoriteEntity>>>()
    fun getAllMeals() = viewModelScope.launch {
        repository.getAllFavorite().collect {

            _getAllFavorite.postValue(it)


        }
    }

}