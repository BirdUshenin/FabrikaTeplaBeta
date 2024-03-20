package com.example.heatfactory.presentation.ui.home.CategoryItem

import com.example.heatfactory.data.CategoryItem
import com.example.heatfactory.data.Item

data class CategoryScreenState (
    val loading: Boolean = true,
    val list: List<CategoryItem> = emptyList(),
    val error: Boolean = false
)
