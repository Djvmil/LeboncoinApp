package com.sideproject.domain.repository.album

import com.sideproject.domain.models.album.Album

interface AlbumLocalRepository {
    suspend fun saveAlbum(album: Album)
    suspend fun findAlbum(id: String): Album
    suspend fun getAlbums(): List<Album>
    suspend fun getAlbumByPage(page: Int): List<Album>
}
