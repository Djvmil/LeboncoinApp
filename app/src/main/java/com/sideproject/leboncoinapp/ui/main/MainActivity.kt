package com.sideproject.leboncoinapp.ui.main

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.rememberNavController
import com.sideproject.leboncoinapp.R
import com.sideproject.leboncoinapp.ui.navigation.LeboncoinNavHost
import com.sideproject.leboncoinapp.ui.theme.LeboncoinAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var splashScreen: SplashScreen
    private var keepSplashOpen = true

    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= 31) {
            // init new splash screen api for Android 12+
            splashScreen = installSplashScreen()
        } else {
            // use old approach
            setTheme(R.style.Theme_App_Starting)
        }

        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        viewModel.showSplashScreen()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel._uiState.collect(::handleUiState)
            }
        }
    }

    private fun handleUiState(uiState: MainUiState) {
        if (Build.VERSION.SDK_INT >= 31) {
            splashScreen.setKeepOnScreenCondition {
                this.keepSplashOpen
            }
        }

        // if (!uiState.isLoading) {
        setContent {
            LeboncoinAppTheme {
                AppContent {
                    keepSplashOpen = false
                }
            }
        }
        // }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppContent(onDataLoaded: () -> Unit) {
    val navController = rememberNavController()
    var isLoading by rememberSaveable {
        mutableStateOf(false)
    }

    val mainViewModel: MainViewModel = hiltViewModel()

    LaunchedEffect(key1 = Unit) {
        onDataLoaded()
    }

    mainViewModel.loadAlbums()

    // Log.e("TAG", "AppContent: ${mainViewModel._uiState.collectAsState().value}" )

    Scaffold { innerPadding ->
        LeboncoinNavHost(
            navHostController = navController,
            modifier = Modifier.padding(innerPadding),
            onLoadindEvent = { showLoader ->
                isLoading = showLoader
            },
            onNavigationEvent = { screen ->
                navController.navigate(screen.route)
            },
        )

        if (isLoading) {
            LoaderScreen()
        }
    }
}
