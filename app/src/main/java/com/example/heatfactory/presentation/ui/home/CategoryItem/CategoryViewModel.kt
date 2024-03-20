package com.example.heatfactory.presentation.ui.home.CategoryItem

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.heatfactory.domain.ApiClient
import com.example.heatfactory.presentation.ui.home.CategoryItem.CategoryScreenState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel() {
    private val _state: MutableStateFlow<CategoryScreenState> = MutableStateFlow(CategoryScreenState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            delay(3000)
            categoryItemsRequest()
        }
    }

    private suspend fun categoryItemsRequest() {
        _state.update {
            it.copy(
                loading = true
            )
        }
        try {
            val response = ApiClient.apiService.getBoilers()
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
}

