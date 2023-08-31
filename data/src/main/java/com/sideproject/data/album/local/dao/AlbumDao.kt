package com.sideproject.data.album.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sideproject.data.album.entity.AlbumEntity
import com.sideproject.domain.models.album.Album
import kotlinx.coroutines.flow.Flow

@Dao
interface AlbumDao {

    @Query("SELECT * FROM albums WHERE id = :id LIMIT 1")
    fun findAlbum(id: String): AlbumEntity

    @Query("SELECT * FROM albums")
    fun getAlbums(): PagingSource<Int, AlbumEntity>
    @Query("SELECT (SELECT COUNT(*) FROM albums) == 0")
    fun isEmpty(): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAlbum(albumEntity: AlbumEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAlbums(albumEntity: List<AlbumEntity>)
}
