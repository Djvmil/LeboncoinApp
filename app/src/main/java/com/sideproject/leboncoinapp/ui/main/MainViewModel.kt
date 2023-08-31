package com.sideproject.leboncoinapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sideproject.domain.models.album.Album
import com.sideproject.domain.use_cases.GetAlbumsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: GetAlbumsUseCase,
) : ViewModel() {
    companion object {
        private val SPLASHSCREEN_DURATION: Long = 3000
    }

    val albumsPagingSource: Flow<PagingData<Album>> = useCase.callLocal().cachedIn(viewModelScope)

    private val uiState: MutableStateFlow<MainUiState> = MutableStateFlow(MainUiState())
    val _uiState = uiState.asStateFlow()

    private val isLaunchSplachScreen = MutableStateFlow(true)
    val _isLaunchSplachScreen = isLaunchSplachScreen.asStateFlow()

    fun showSplashScreen() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(SPLASHSCREEN_DURATION)
            loadAlbums()
            isLaunchSplachScreen.value = false
        }
    }

    fun loadAlbums() {
        viewModelScope.launch(Dispatchers.IO) {
            uiState.update {
                it.copy(
                    isLoading = true,
                )
            }

            if (useCase.callIsEmpty()) {
                useCase.callRemote()
            }

            uiState.update {
                it.copy(
                    isLoading = false,
                )
            }
        }
    }
}
