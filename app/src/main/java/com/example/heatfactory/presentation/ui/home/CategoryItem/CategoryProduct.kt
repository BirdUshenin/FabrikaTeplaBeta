package com.example.heatfactory.presentation.ui.home.CategoryItem

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.heatfactory.data.CategoryItem
import com.example.heatfactory.data.Item

@Composable
fun CategoryProduct(
    item: Item, onBackPressed: () -> Unit,
    state: CategoryScreenState,
    selectedItem: MutableState<CategoryItem?>,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Name: ${item.name}", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Description: ${item.description}")

        LazyColumn(

        ){
            state.list.forEach { item ->
                item {
                    CategoryProductList(
                        item = item,
                        onItemClick = { selectedItem.value = it }
                    )
                }
            }
        }
    }
    BackHandler {
        onBackPressed()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryProductList(
    item: CategoryItem,
    onItemClick: (CategoryItem) -> Unit
){
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        onClick = { onItemClick(item) }
    ) {
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
            Text(text = item.name)
        }
    }
}

