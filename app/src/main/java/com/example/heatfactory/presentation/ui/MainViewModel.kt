package com.example.heatfactory.presentation.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.heatfactory.data.Item
import com.example.heatfactory.domain.ApiClient
import com.example.heatfactory.navigation.NavBarItem

class MainViewModel: ViewModel() {
    private val _selectedNavItem = MutableLiveData<NavBarItem>(NavBarItem.Home)
    val selectedNavItem: LiveData<NavBarItem> = _selectedNavItem

    fun selectNavItem(item: NavBarItem){
        _selectedNavItem.value = item
    }

    suspend fun fetchItems(): List<Item> {
        return try {
            val response = ApiClient.apiService.getItems()
            response
        } catch (e: Throwable) {
            Log.d("Ushennnnnnn", "${e.message}")
            emptyList()
        }
    }
}