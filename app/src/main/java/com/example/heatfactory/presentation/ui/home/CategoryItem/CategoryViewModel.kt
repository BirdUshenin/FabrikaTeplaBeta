package com.example.heatfactory.presentation.ui.home.CategoryItem

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.heatfactory.data.CategoryItem
import com.example.heatfactory.domain.ApiClient
import com.example.heatfactory.presentation.ui.home.CategoryItem.CategoryScreenState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel() {
    private val _state: MutableStateFlow<CategoryScreenState> = MutableStateFlow(CategoryScreenState())
    val state = _state.asStateFlow()
    private val _id = MutableStateFlow(0)
    val id: StateFlow<Int> = _id

    init {
        viewModelScope.launch {
            delay(3000)
            categoryItemsRequest()
        }
        viewModelScope.launch {

            delay(3000)
            id.collect { newId ->
                categoryItemsRequest()
            }
        }
    }

    private suspend fun categoryItemsRequest() {
        _state.update {
            it.copy(
                loading = true
            )
        }
        try {
            val response = when (id.value) {
                1 -> ApiClient.apiService.getBoilers()
                2 -> ApiClient.apiService.getRadiators()
                else -> throw IllegalArgumentException("Unexpected id value: ${id.value}")
            }
            Log.d("Ushennn", "${id.value}")
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
    fun updateId(newId: Int) {
        _id.value = newId
    }
}

