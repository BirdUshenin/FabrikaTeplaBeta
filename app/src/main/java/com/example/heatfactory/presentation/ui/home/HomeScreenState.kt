package com.example.heatfactory.presentation.ui.home

import com.example.heatfactory.data.Item

data class HomeScreenState(
    val loading: Boolean = true,
    val list: List<Item> = emptyList(),
    val error: Boolean = false
)
