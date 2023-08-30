package com.sideproject.leboncoinapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sideproject.leboncoinapp.core.shareprefs.LeboncoinSharedPrefsImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val sharedPrefsImpl: LeboncoinSharedPrefsImpl,
) : ViewModel() {
    companion object {
        private val SPLASHSCREEN_DURATION: Long = 1000
    }

    private val uiState: MutableStateFlow<MainUiState> = MutableStateFlow(MainUiState())
    val _uiState = uiState.asStateFlow()

    fun showSplashScreen() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(SPLASHSCREEN_DURATION)
            loadAlbums()
        }
    }

    fun loadAlbums() {
        viewModelScope.launch(Dispatchers.IO) {
            uiState.update {
                it.copy(
                    isLoading = true,
                )
            }

            delay(1000)

            uiState.update {
                it.copy(
                    isLoading = false,
                )
            }
        }
    }
}
