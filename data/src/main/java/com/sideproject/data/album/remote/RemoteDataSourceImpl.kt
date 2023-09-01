package com.sideproject.data.album.remote

import com.sideproject.data.album.remote.api.AlbumAPIService
import com.sideproject.domain.models.album.Album
import com.sideproject.domain.source.RemoteDataSource
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val remoteService: AlbumAPIService) : RemoteDataSource {
    override suspend fun getAlbums(): List<Album> {
        return remoteService.getAlbums()
    }
}
