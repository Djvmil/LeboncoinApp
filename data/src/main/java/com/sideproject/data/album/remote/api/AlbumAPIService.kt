package com.sideproject.data.album.remote.api

import com.sideproject.domain.models.album.Album
import retrofit2.http.GET

interface AlbumAPIService {
    @GET("technical-test.json")
    suspend fun getAlbums(): List<Album>
}
