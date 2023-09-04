package com.sideproject.leboncoinapp.ui.detail

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.sideproject.domain.models.album.Album

@Composable
fun DetailScreen(
    albumId: Int
) {
    val detailsViewModel: DetailsViewModel = hiltViewModel()
    detailsViewModel.findLocalAlbums(albumId)

    val album by remember {
        mutableStateOf(detailsViewModel.album)
    }

    Log.e("TAG", "DetailContent: albumId $albumId")
    DetailContent(album)
}

@Composable
fun DetailContent(album: Album?) {
    Log.e("TAG", "DetailContent: $album")
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            model = album?.thumbnailUrl,
            contentDescription = album?.title,
            modifier = Modifier.fillMaxWidth().height(250.dp)
                .padding(15.dp).clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Fit,
        )
        Card(
            modifier = Modifier.padding(10.dp)
                .fillMaxSize(),
            shape = MaterialTheme.shapes.medium,
        ) {
            Column(Modifier.padding(8.dp)) {
                Text(
                    text = album?.title!!,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
        }
    }
}
