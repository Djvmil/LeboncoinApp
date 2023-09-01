package com.sideproject.domain.usecase

import com.sideproject.domain.models.album.Album

interface SaveAlbumsUseCase {
    suspend operator fun invoke(albums: List<Album>)
}
