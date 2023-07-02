package com.example.jetweatherforecast.screens.favourites

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetweatherforecast.model.Favourite
import com.example.jetweatherforecast.repository.WeatherDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(private val repository: WeatherDbRepository): ViewModel() {
    private val _favList = MutableStateFlow<List<Favourite>>(emptyList())
    val favList = _favList.asStateFlow()

    init {
        viewModelScope.launch (Dispatchers.IO){
            repository.getFavourites().distinctUntilChanged()
                .collect{ listOfFavs ->
                    if(listOfFavs.isNullOrEmpty()){
                        Log.d("TAG404", ": Empty favs")
                    }else{
                        _favList.value = listOfFavs
                        Log.d("FAVS", ":${favList.value}")
                }
            }
        }
    }

    fun insertFavourite(favourite: Favourite)
    = viewModelScope.launch { repository.insertFavourite(favourite) }

    fun updateFavourite(favourite: Favourite)
    = viewModelScope.launch { repository.updateFavourite(favourite) }

    fun deleteFavourite(favourite: Favourite)
    = viewModelScope.launch { repository.deleteFavourite(favourite) }


}