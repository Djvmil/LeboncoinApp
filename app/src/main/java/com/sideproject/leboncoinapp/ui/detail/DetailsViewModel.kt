package com.sideproject.leboncoinapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sideproject.domain.models.album.Album
import com.sideproject.domain.usecase.FindAlbumUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val findAlbumUseCase: FindAlbumUseCase,
) : ViewModel() {
    var album: Album? = null

    fun findLocalAlbums(id: Int) = viewModelScope.launch {
        album = findAlbumUseCase(id)
    }
}
