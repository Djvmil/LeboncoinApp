package com.sideproject.data.album.usecase

import com.sideproject.domain.models.album.Album
import com.sideproject.domain.repository.album.AlbumDataRepository
import com.sideproject.domain.usecase.FindAlbumUseCase
import javax.inject.Inject

class FindAlbumUseCaseImpl @Inject constructor(
    private val albumDataRepository: AlbumDataRepository,
) : FindAlbumUseCase {
    override suspend fun invoke(id: Int): Album = albumDataRepository.findAlbum(id.toString())
}
