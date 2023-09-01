package com.sideproject.domain.source

import com.sideproject.domain.models.album.Album

interface RemoteDataSource {
    suspend fun getAlbums(): List<Album>
}
