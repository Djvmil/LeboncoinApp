package com.sideproject.leboncoinapp.ui.main

import com.sideproject.domain.models.album.Album

data class MainUiState(
    val isLoading: Boolean = true,
    val isGetRemoteData: Boolean = true,
    val data: List<Album>? = null,
)
