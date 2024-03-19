package com.example.heatfactory.presentation.ui.home

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavBackStackEntry
import com.example.heatfactory.data.Item
import com.example.heatfactory.domain.ApiClient
import com.example.heatfactory.navigation.NavBarItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _state: MutableStateFlow<HomeScreenState> = MutableStateFlow(HomeScreenState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            delay(3000)
            fetchItems()
        }
    }

    private suspend fun fetchItems() {
        _state.update {
            it.copy(
                loading = true
            )
        }
        try {
            val response = ApiClient.apiService.getItems()
            _state.update {
                it.copy(
                    loading = false,
                    list = response
                )
            }
        } catch (e: Throwable) {
            Log.d("Ushennnnnnn", "${e.message}")
            _state.update {
                it.copy(
                    error = true
                )
            }
        }
    }


    fun onBackPress(){

    }

    private val backStackEntries = mutableStateListOf<NavBackStackEntry>()

    fun addToBackStack(entry: NavBackStackEntry) {
        backStackEntries.add(entry)
    }

    fun removeFromBackStack(entry: NavBackStackEntry) {
        backStackEntries.remove(entry)
    }

//    fun onClick() {
//        _state.update {
//            it.copy(
//                error = true
//            )
//        }
//    }

//    fun back() {
//        _state.update {
//            it.copy(
//                loading = true
//            )
//        }
//    }
}