package com.sideproject.domain.repository.album

import androidx.paging.PagingData
import com.sideproject.domain.models.album.Album
import kotlinx.coroutines.flow.Flow

interface AlbumLocalRepository {
    suspend fun saveAlbum(album: Album)
    suspend fun findAlbum(id: String): Album
    suspend fun isEmpty(): Boolean
    fun getAlbums(): Flow<PagingData<Album>>
}
