package com.sideproject.leboncoinapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sideproject.data.album.entity.AlbumEntity
import com.sideproject.data.album.local.dao.AlbumDao

@Database(entities = [AlbumEntity::class], version = 1, exportSchema = false)
abstract class LeboncoinAppDatabase : RoomDatabase() {
    abstract fun albumDao(): AlbumDao
}
