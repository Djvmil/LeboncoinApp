package com.sideproject.data.album.usecase

import com.sideproject.domain.models.album.Album
import com.sideproject.domain.models.album.Resource
import com.sideproject.domain.repository.album.AlbumDataRepository
import com.sideproject.domain.usecase.GetAlbumsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocalAlbumsUseCaseImpl @Inject constructor(
    private val albumDataRepository: AlbumDataRepository,
) : GetAlbumsUseCase {
    override suspend fun invoke(): Flow<Resource<List<Album>>> = albumDataRepository.getAlbums()
}
