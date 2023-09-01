package com.sideproject.data.album.usecase

import com.sideproject.domain.models.album.Album
import com.sideproject.domain.repository.album.AlbumDataRepository
import com.sideproject.domain.usecase.SaveAlbumsUseCase
import javax.inject.Inject

class SaveAlbumsUseCaseImpl @Inject constructor(
    private val albumDataRepository: AlbumDataRepository,
) : SaveAlbumsUseCase {
    override suspend fun invoke(albums: List<Album>) = albumDataRepository.saveAlbums(albums)
}
