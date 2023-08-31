package com.sideproject.domain.models.album

data class Album(
    val albumId: Int? = null,
    val id: Int,
    val title: String? = null,
    val url: String? = null,
    val thumbnailUrl: String? = null,
    var dateCreated: String? = null,
)
