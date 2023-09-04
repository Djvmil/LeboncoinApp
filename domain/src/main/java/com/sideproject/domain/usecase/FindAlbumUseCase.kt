package com.sideproject.domain.usecase

import com.sideproject.domain.models.album.Album

interface FindAlbumUseCase {
    suspend operator fun invoke(id: Int): Album
}
