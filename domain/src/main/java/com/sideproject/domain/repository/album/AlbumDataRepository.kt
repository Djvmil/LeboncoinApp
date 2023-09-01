package com.sideproject.domain.repository.album

import com.sideproject.domain.models.album.Album
import com.sideproject.domain.models.album.Resource
import kotlinx.coroutines.flow.Flow

interface AlbumDataRepository {
    suspend fun saveAlbum(album: Album)
    suspend fun saveAlbums(albums: List<Album>)
    suspend fun findAlbum(id: String): Album
    suspend fun isListAlbumEmpty(): Boolean
    suspend fun getAlbums(): Flow<Resource<List<Album>>>
    suspend fun getRemoteAlbums(): Flow<Resource<List<Album>>>
}
