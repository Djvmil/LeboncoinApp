package com.sideproject.leboncoinapp.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sideproject.domain.models.album.Album
import com.sideproject.domain.models.album.Resource
import com.sideproject.domain.usecase.CheckIfAlbumEmptyUseCase
import com.sideproject.domain.usecase.GetAlbumsUseCase
import com.sideproject.domain.usecase.SaveAlbumsUseCase
import com.sideproject.leboncoinapp.core.di.GetRemoteAlbumsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @GetRemoteAlbumsUseCase private val getRemoteAlbumsUseCase: GetAlbumsUseCase,
    private val checkIfAlbumEmptyUseCase: CheckIfAlbumEmptyUseCase,
    private val saveAlbumsUseCase: SaveAlbumsUseCase,
) : ViewModel() {
    companion object {
        private val SPLASHSCREEN_DURATION: Long = 3000
    }

    private var uiState = MutableStateFlow<Resource<List<Album>>>(Resource.Loading)
    val _uiState = uiState.asStateFlow()

    private val isLaunchSplachScreen = MutableStateFlow(true)
    val _isLaunchSplachScreen = isLaunchSplachScreen.asStateFlow()

    init {
        checkIfEmpty()
    }

    fun showSplashScreen() {
        viewModelScope.launch {
            delay(SPLASHSCREEN_DURATION)
            isLaunchSplachScreen.value = false
        }
    }

    fun checkIfEmpty() = viewModelScope.launch(Dispatchers.IO) {
        if (checkIfAlbumEmptyUseCase()) {
            getRemoteAlbums()
        } else {
            viewModelScope.launch {
                uiState.emit(Resource.Finish)
            }
        }
    }

    fun getRemoteAlbums() = viewModelScope.launch {
        getRemoteAlbumsUseCase()
            .flowOn(Dispatchers.IO)
            .catch { e ->
                Log.d("TAG", "getRemoteAlbums error: ${e.localizedMessage}")
            }.collect { result ->
                when (result) {
                    is Resource.Success -> {
                        insertAlbums(result.data)
                        uiState.emit(Resource.Finish)
                    }
                    is Resource.Error -> Log.d("TAG", "insertAlbums: ${result.throwable.localizedMessage}")
                    else -> {
                        // Loading or Finish
                    }
                }
            }
    }

    private fun insertAlbums(datas: List<Album>) = viewModelScope.launch(Dispatchers.IO) {
        saveAlbumsUseCase.invoke(datas)
    }
}
