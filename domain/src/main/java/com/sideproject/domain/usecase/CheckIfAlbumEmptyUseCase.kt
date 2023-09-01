package com.sideproject.domain.usecase

interface CheckIfAlbumEmptyUseCase {
    suspend operator fun invoke(): Boolean
}
