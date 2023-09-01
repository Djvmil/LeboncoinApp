package com.sideproject.domain.usecase

import com.sideproject.domain.models.album.Album
import com.sideproject.domain.models.album.Resource
import kotlinx.coroutines.flow.Flow

interface GetAlbumsUseCase {
    suspend operator fun invoke(): Flow<Resource<List<Album>>>
}
