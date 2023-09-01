package com.sideproject.leboncoinapp.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sideproject.domain.models.album.Album
import com.sideproject.domain.models.album.Resource
import com.sideproject.domain.usecase.GetAlbumsUseCase
import com.sideproject.leboncoinapp.core.di.GetLocalAlbumsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @GetLocalAlbumsUseCase private val getLocalAlbumsUseCase: GetAlbumsUseCase,
) : ViewModel() {

    private var uiState = MutableStateFlow<Resource<List<Album>>>(Resource.Loading)
    val _uiState = uiState.asStateFlow()

    init {
        getLocalAlbums()
    }

    fun getLocalAlbums() = viewModelScope.launch {
        getLocalAlbumsUseCase()
            .flowOn(Dispatchers.IO)
            .catch { e ->
                // handle exception
                Log.d("TAG", "getLocalAlbums: ${e.localizedMessage}")
            }.collect {
                uiState.emit(it)
            }
    }
}
