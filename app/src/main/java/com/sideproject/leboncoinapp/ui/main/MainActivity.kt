package com.sideproject.leboncoinapp.ui.main

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.rememberNavController
import com.sideproject.domain.models.album.Resource
import com.sideproject.leboncoinapp.R
import com.sideproject.leboncoinapp.ui.navigation.LeboncoinNavHost
import com.sideproject.leboncoinapp.ui.theme.LeboncoinAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @VisibleForTesting
    internal val viewModel: MainViewModel by viewModels()

    private lateinit var splashScreen: SplashScreen

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

    private fun handleUiState(uiState: Resource<Any>) {
        if (Build.VERSION.SDK_INT >= 31) {
            splashScreen.setKeepOnScreenCondition {
                viewModel._isLaunchSplachScreen.value
            }
        }
        setContent {
            LeboncoinAppTheme {
                AppContent(uiState)
            }
        }
    }

    companion object {
        const val TAG = "MainActivity"
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppContent(uiState: Resource<Any>) {
    val navController = rememberNavController()

    when (uiState) {
        is Resource.Loading -> {
            LoaderScreen()
        }
        is Resource.Finish -> {
            Scaffold { innerPadding ->
                LeboncoinNavHost(
                    navHostController = navController,
                    modifier = Modifier.padding(innerPadding),
                    onNavigationEvent = { route ->
                        navController.navigate(route)
                    },
                )
            }
        }
        else -> {
            Log.d(MainActivity.TAG, "handleUiState: Resource.Error or Success")
        }
    }
}
