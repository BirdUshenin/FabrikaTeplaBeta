package com.example.heatfactory.presentation.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.heatfactory.data.Item

@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    paddingValues: PaddingValues,
) {
    val state by viewModel.state.collectAsState()
    val selectedItem = remember { mutableStateOf<Item?>(null) }
    val navigateBack: () -> Unit = {
        selectedItem.value = null
    }
    val rememberStateScroll = rememberLazyListState()

    CategoryScreen(selectedItem, navigateBack, state, paddingValues, rememberStateScroll)

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Commodity(
    item: Item,
    onItemClick: (Item) -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        onClick = { onItemClick(item) }
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