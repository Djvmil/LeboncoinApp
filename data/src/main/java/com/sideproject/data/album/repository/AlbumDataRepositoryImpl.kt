package com.sideproject.data.album.repository

import com.sideproject.data.album.entity.toAlbum
import com.sideproject.data.album.entity.toAlbumEntity
import com.sideproject.data.album.local.dao.AlbumDao
import com.sideproject.domain.models.album.Album
import com.sideproject.domain.models.album.Resource
import com.sideproject.domain.repository.album.AlbumDataRepository
import com.sideproject.domain.source.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AlbumDataRepositoryImpl @Inject constructor(
    private val albumDao: AlbumDao,
    private val remoteDataSource: RemoteDataSource,
) : AlbumDataRepository {

    override suspend fun saveAlbum(album: Album) {
        albumDao.saveAlbum(album.toAlbumEntity())
    }
    override suspend fun saveAlbums(albums: List<Album>) {
        albumDao.saveAlbums(albums.map { it.toAlbumEntity() })
    }

    override suspend fun findAlbum(id: String): Album {
        return albumDao.findAlbum(id).toAlbum()
    }

    override suspend fun isListAlbumEmpty(): Boolean {
        return albumDao.isEmpty()
    }

    override suspend fun getRemoteAlbums(): Flow<Resource<List<Album>>> = flow {
        emit(Resource.Loading)
        try {
            emit(Resource.Success(remoteDataSource.getAlbums()))
        } catch (t: Throwable) {
            emit(Resource.Error(t))
        }
    }

    override suspend fun getAlbums(): Flow<Resource<List<Album>>> = flow {
        emit(Resource.Loading)
        try {
            emit(Resource.Success(albumDao.getAlbums().map { albums -> albums.toAlbum() }))
        } catch (t: Throwable) {
            emit(Resource.Error(t))
        }
    }
}
