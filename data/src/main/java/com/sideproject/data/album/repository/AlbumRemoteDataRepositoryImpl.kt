package com.sideproject.data.album.repository

import android.util.Log
import androidx.paging.map
import com.sideproject.data.album.entity.AlbumEntity
import com.sideproject.data.album.entity.toAlbumEntity
import com.sideproject.data.album.local.dao.AlbumDao
import com.sideproject.data.album.remote.api.AlbumAPIService
import com.sideproject.domain.repository.album.AlbumRemoteRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AlbumRemoteDataRepositoryImpl @Inject constructor(
    private val api: AlbumAPIService,
    private val dao: AlbumDao,
) : AlbumRemoteRepository {

    override suspend fun getAlbums() {
        try {
            val response = api.getAlbums()
            if (response.isSuccessful) {
                response.body()?.let { albums ->
                    val data = albums.map { album ->
                        album.toAlbumEntity()
                    }
                    insertAlbums(data)
                }
            }
        } catch (e: Exception) {
            Log.d("TTT", e.message.toString())
        }
    }

    private fun insertAlbums(albums: List<AlbumEntity>) =
        dao.saveAlbums(albums)
}
