package com.sideproject.data.album.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.sideproject.data.album.entity.AlbumEntity
import com.sideproject.data.album.entity.toAlbum
import com.sideproject.data.album.entity.toAlbumEntity
import com.sideproject.data.album.local.dao.AlbumDao
import com.sideproject.domain.models.album.Album
import com.sideproject.domain.repository.album.AlbumLocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AlbumLocalDataRepositoryImpl @Inject constructor(
    private val albumDao: AlbumDao,
) : AlbumLocalRepository {

    override suspend fun saveAlbum(album: Album) {
        albumDao.saveAlbum(album.toAlbumEntity())
    }

    override suspend fun findAlbum(id: String): Album {
        return albumDao.findAlbum(id).toAlbum()
    }

    override suspend fun isEmpty(): Boolean {
        return albumDao.isEmpty()
    }

    override fun getAlbums(): Flow<PagingData<Album>> {
        return Pager(
            PagingConfig(
                pageSize = 20,
                initialLoadSize = 20,
            ),
        ) {
            albumDao.getAlbums()
        }.flow
            .map { pagingData: PagingData<AlbumEntity> ->
                pagingData.map { albumEntity ->
                    albumEntity.toAlbum()
                }
            }
    }
}
