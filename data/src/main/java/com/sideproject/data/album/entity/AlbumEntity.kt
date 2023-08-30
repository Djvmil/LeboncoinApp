package com.sideproject.data.album.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sideproject.data.constants.Constants
import com.sideproject.data.utils.toSimpleString
import com.sideproject.domain.models.album.Album

@Entity(tableName = Constants.ALBUM_TABLE)
data class AlbumEntity(
    @PrimaryKey
    var id: Int? = null,
    var albumId: Int? = null,
    var title: String? = null,
    var url: String? = null,
    var thumbnailUrl: String? = null,
    var dateCreated: String? = toSimpleString(),
)

fun Album.toAlbumEntity() = AlbumEntity(
    albumId = this.albumId,
    id = this.id,
    title = this.title,
    url = this.url,
    thumbnailUrl = this.thumbnailUrl,
)

fun AlbumEntity.toAlbum() = Album(
    albumId = this.albumId,
    id = this.id,
    title = this.title,
    url = this.url,
    thumbnailUrl = this.thumbnailUrl,
    dateCreated = this.url,
)
