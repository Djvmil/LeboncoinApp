package com.sideproject.leboncoinapp.database

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sideproject.data.album.local.dao.AlbumDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Provider

class AlbumCallBack(
    private val provider: Provider<AlbumDao>,
) : RoomDatabase.Callback() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        applicationScope.launch(Dispatchers.IO) {
            populateDatabase()
        }
    }

    private fun populateDatabase() {
       // provider.get().saveAlbums()
    }
}
