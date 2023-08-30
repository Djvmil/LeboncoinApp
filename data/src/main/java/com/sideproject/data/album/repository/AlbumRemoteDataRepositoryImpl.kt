package com.sideproject.data.album.repository

import com.sideproject.domain.models.album.Album
import com.sideproject.domain.repository.album.AlbumRemoteRepository
import javax.inject.Inject

class AlbumRemoteDataRepositoryImpl @Inject constructor() : AlbumRemoteRepository {

    override suspend fun getAlbums(): List<Album> {
        TODO("Not yet implemented")
    }

    override suspend fun getAlbumByPage(page: Int): List<Album> {
        TODO("Not yet implemented")
    }
}
