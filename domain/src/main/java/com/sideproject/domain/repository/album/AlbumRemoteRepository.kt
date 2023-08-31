package com.sideproject.domain.repository.album

interface AlbumRemoteRepository {
    suspend fun getAlbums()
}
