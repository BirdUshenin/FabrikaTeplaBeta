package com.example.heatfactory.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.heatfactory.navigation.NavBarItem

class MainViewModel: ViewModel() {
    private val _selectedNavItem = MutableLiveData<NavBarItem>(NavBarItem.Home)
    val selectedNavItem: LiveData<NavBarItem> = _selectedNavItem

    fun selectNavItem(item: NavBarItem){
        _selectedNavItem.value = item
    }

}