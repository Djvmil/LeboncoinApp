package com.sideproject.domain.repository.album

import com.sideproject.domain.models.album.Album

interface AlbumRemoteRepository {
    suspend fun getAlbums(): List<Album>
    suspend fun getAlbumByPage(page: Int): List<Album>
}