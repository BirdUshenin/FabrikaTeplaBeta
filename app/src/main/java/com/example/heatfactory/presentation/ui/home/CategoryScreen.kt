package com.example.heatfactory.presentation.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.heatfactory.R
import com.example.heatfactory.data.Item

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CategoryScreen(
    selectedItem: MutableState<Item?>,
    navigateBack: () -> Unit,
    state: HomeScreenState,
    paddingValues: PaddingValues,
    rememberStateScroll: LazyListState
) {
    if (selectedItem.value != null) {
        CategoryProduct(selectedItem.value!!, navigateBack)
    } else {
        when {
            state.error -> {
                Text(text = "Error")
            }

            state.loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            else -> {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(Color(0xFFFA6C37)),
                            title = {
                                Text(
                                    "Фабрика Тепла",
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    color = Color.White
                                )
                            },
                            navigationIcon = {
                                IconButton(onClick = { /* doSomething() */ }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.logo),
                                        contentDescription = "Localized description",
                                        tint = Color.White,
                                        modifier = Modifier.size(30.dp)
                                    )
                                }
                            },
                            actions = {
                                IconButton(onClick = { /* doSomething() */ }) {
                                    Icon(
                                        imageVector = Icons.Filled.Favorite,
                                        contentDescription = "Localized description"
                                    )
                                }
                            }
                        )
                    },
                ) {
                    LazyColumn(
                        state = rememberStateScroll,
                        modifier = Modifier.padding(paddingValues),
                        contentPadding = it
                    ) {
                        state.list.forEach { item ->
                            item {
                                Commodity(
                                    item = item,
                                    onItemClick = {
                                        selectedItem.value = it
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}