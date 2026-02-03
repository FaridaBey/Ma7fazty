package com.example.ma7faza.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ma7faza.ui.theme.Purple0

@Composable
fun LoadingIndicator(
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    screenWidth: Dp,
    screenHeight: Dp,
) {
    if (isLoading) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                color = Purple0,
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(screenWidth * 0.3f, screenHeight * 0.3f)
            )
        }
    }
}

