package com.sideproject.data.album.repository

import com.sideproject.data.album.entity.toAlbum
import com.sideproject.data.album.entity.toAlbumEntity
import com.sideproject.data.album.local.dao.AlbumDao
import com.sideproject.domain.models.album.Album
import com.sideproject.domain.repository.album.AlbumLocalRepository
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

    override suspend fun getAlbums(): List<Album> {
        val albums = albumDao.getAlbums()
        return albums.map {
            it.toAlbum()
        }
    }

    override suspend fun getAlbumByPage(page: Int): List<Album> {
        TODO("Not yet implemented")
    }
}
