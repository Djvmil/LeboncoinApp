package com.sideproject.leboncoinapp.persistence

import com.sideproject.data.album.local.dao.AlbumDao
import com.sideproject.leboncoinapp.MockTestUtil.mockAlbumList
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class AlbumDaoTest : LocalDatabase() {

    private lateinit var albumDao: AlbumDao

    @Before
    fun init() {
        albumDao = db.albumDao()
    }

    @Test
    fun insertAndLoadAlbumListTest() = runTest {
        val mockDataList = mockAlbumList()

        if (albumDao.isEmpty()) {
            albumDao.saveAlbums(mockDataList)
        }

        val loadFromDB = albumDao.getAlbums()
        assertThat(loadFromDB.toString(), `is`(mockDataList.toString()))

    /*val loadFromDB = albumDao.findAlbum(1.toString())

    assertThat(loadFromDB.toAlbum().albumId.toString(), `is`(mockDataList.find { it.id == 1 }?.albumId.toString()))
    assertThat(loadFromDB.toAlbum().id.toString(), `is`(mockDataList.find { it.id == 1 }?.id.toString()))
    assertThat(loadFromDB.toAlbum().title.toString(), `is`(mockDataList.find { it.id == 1 }?.title.toString()))*/
    }
}
