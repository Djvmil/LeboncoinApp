package com.sideproject.leboncoinapp.ui.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.sideproject.domain.models.album.Album
import com.sideproject.domain.models.album.Resource
import com.sideproject.leboncoinapp.ui.main.LoaderScreen
import com.sideproject.leboncoinapp.ui.main.MainActivity
import com.sideproject.leboncoinapp.ui.navigation.Details
import kotlinx.coroutines.flow.StateFlow

@Composable
fun HomeScreen(
    onNavigationEvent: (String) -> Unit,
) {
    val homeViewModel: HomeViewModel = hiltViewModel()

    HomeContent(homeViewModel._uiState, onNavigationEvent)
}

@Composable
fun HomeContent(
    albumState: StateFlow<Resource<List<Album>>>,
    onNavigationEvent: (String) -> Unit,
) {
    when (val _albumState = albumState.collectAsState().value) {
        is Resource.Loading -> {
            LoaderScreen()
        }
        is Resource.Success -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
            ) {
                items(
                    count = _albumState.data.size,
                ) { index: Int ->
                    AlbumCard(_albumState.data[index], onNavigationEvent)
                }
            }
        }
        else -> {
            Log.d(MainActivity.TAG, "handleUiState: Resource.Error or Resource.Finish")
            Log.e("TAG", "HomeScreen: Else ")
        }
    }
}

@Composable
fun AlbumCard(album: Album, onNavigationEvent: (String) -> Unit) {
    Card(
        modifier = Modifier.padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight().clickable {
                onNavigationEvent("${Details.route}/${album.id}")
            },
        shape = MaterialTheme.shapes.medium,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = album.thumbnailUrl,
                contentDescription = album.title,
                modifier = Modifier.size(130.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Fit,
            )

            Column(Modifier.padding(8.dp)) {
                Text(
                    text = album.title!!,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    // HomeScreen()
}
