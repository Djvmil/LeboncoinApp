package com.sideproject.domain.use_cases

import androidx.paging.PagingData
import com.sideproject.domain.models.album.Album
import com.sideproject.domain.repository.album.AlbumLocalRepository
import com.sideproject.domain.repository.album.AlbumRemoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAlbumsUseCase @Inject constructor(
    private val albumLocalRepository: AlbumLocalRepository,
    private val albumRemoteRepository: AlbumRemoteRepository,
) {

    suspend fun callIsEmpty(): Boolean {
        return albumLocalRepository.isEmpty()
    }

    suspend fun callRemote() {
        return albumRemoteRepository.getAlbums()
    }

    fun callLocal(): Flow<PagingData<Album>> {
        return albumLocalRepository.getAlbums()
    }
}
