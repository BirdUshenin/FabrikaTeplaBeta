package com.example.heatfactory.presentation.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.heatfactory.data.Item

@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    paddingValues: PaddingValues,
){
    val itemsState = remember { mutableStateOf<List<Item>>(emptyList()) }

    LaunchedEffect(Unit) {
        val items = fetchItems()
        itemsState.value = items
    }

    val items = itemsState.value

    LazyColumn {
        items.forEach { item ->
            item {
                Commodity(item = item)
            }
        }
    }
}