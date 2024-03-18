package com.example.heatfactory.presentation.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.heatfactory.data.Item
import com.example.heatfactory.domain.ApiClient
import com.example.heatfactory.navigation.NavBarItem
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun MainScreen() {

    val itemsState = remember { mutableStateOf<List<Item>>(emptyList()) }

    LaunchedEffect(Unit) {
        val items = fetchItems()
        itemsState.value = items
    }

    val items = itemsState.value

    val snackBarHostState = remember {
        SnackbarHostState()
    }

    val scope = rememberCoroutineScope()

    val fabIsVisible = remember {
        mutableStateOf(true)
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
        floatingActionButton = {
            if (fabIsVisible.value) {
                FloatingActionButton(
                    onClick = {
                        scope.launch {
                            val snackAction = snackBarHostState.showSnackbar(
                                message = "This is PARTA",
                                actionLabel = "Hide FAB",
                                duration = SnackbarDuration.Long
                            )
                            if (snackAction == SnackbarResult.ActionPerformed) {
                                fabIsVisible.value = false
                            }
                        }
                    }
                ) {
                    Icon(
                        Icons.Filled.Favorite,
                        contentDescription = null
                    )
                }
            }
        },
        bottomBar = {
            NavigationBar {
                val selectedPosition = remember {
                    mutableIntStateOf(0)
                }
                val items = listOf(
                    NavBarItem.Home,
                    NavBarItem.Favorite,
                    NavBarItem.Profile
                )
                items.forEachIndexed { index, it ->
                    NavigationBarItem(
                        selected = selectedPosition.intValue == index,
                        onClick = { selectedPosition.intValue = index },
                        icon = {
                            Icon(
                                it.icon,
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(text = stringResource(id = it.titleResId))
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.Blue,
                            unselectedIconColor = Color.Black,
                            selectedTextColor = Color.Blue,
                            unselectedTextColor = Color.Black
                        )
                    )
                }
            }
        }
    ) {
        LazyColumn {
            items.forEach { item ->
                item {
                    Commodity(item = item)
                }
            }
        }
    }

}

suspend fun fetchItems(): List<Item> {
    return try {
        val response = ApiClient.apiService.getItems()
        response
    } catch (e: Exception) {
        Log.d("Ushennnnnnn", "${e.message}")
        emptyList()
    }
}

@Composable
fun Commodity(item: Item) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
    ) {
        Title(item = item)
        Image(
            painter = rememberAsyncImagePainter(
                item.imageSrc
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentDescription = null
        )
        Box(Modifier.padding(15.dp)) {
            Text(text = item.description)
        }
    }
}

@Composable
fun Title(item: Item) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = item.name,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(4.dp))
        }
        Image(
            imageVector = Icons.Rounded.MoreVert,
            contentDescription = null
        )
    }
}




