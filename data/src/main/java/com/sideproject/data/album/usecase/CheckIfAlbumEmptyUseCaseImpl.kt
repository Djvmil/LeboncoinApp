package com.sideproject.data.album.usecase

import com.sideproject.domain.repository.album.AlbumDataRepository
import com.sideproject.domain.usecase.CheckIfAlbumEmptyUseCase
import javax.inject.Inject

class CheckIfAlbumEmptyUseCaseImpl @Inject constructor(
    private val albumDataRepository: AlbumDataRepository,
) : CheckIfAlbumEmptyUseCase {
    override suspend fun invoke(): Boolean = albumDataRepository.isListAlbumEmpty()
}
