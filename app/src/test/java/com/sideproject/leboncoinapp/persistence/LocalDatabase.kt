package com.sideproject.leboncoinapp.persistence

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.sideproject.leboncoinapp.database.LeboncoinAppDatabase
import org.junit.After
import org.junit.Before

abstract class LocalDatabase {
    lateinit var db: LeboncoinAppDatabase

    @Before
    fun initDB() {
        db = Room.inMemoryDatabaseBuilder(getApplicationContext(), LeboncoinAppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }

    @After
    fun closeDB() {
        db.close()
    }
}
