package com.sideproject.leboncoinapp.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.sideproject.leboncoinapp.R

@Composable
fun LoaderScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
            .background(colorResource(id = R.color.teal_200)),
    ) {
        val composition by rememberLottieComposition(
            LottieCompositionSpec.RawRes(R.raw.animation_album),
        )

        LottieAnimation(
            composition = composition,
            modifier = Modifier.size(200.dp),
            iterations = LottieConstants.IterateForever,
        )
    }
}
