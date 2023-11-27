package com.gitmad.duofit.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.gitmad.duofit.viewmodels.MainViewModel
import com.gitmad.duofit.viewmodels.Screen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(mainViewModel: MainViewModel) {
    // Side-effect to navigate after a delay
    LaunchedEffect(key1 = true) {
        delay(1000) // For a 1.5-second delay
        mainViewModel.navigateTo(Screen.Login)
    }

    // Your splash screen content goes here
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Welcome to DuoFit", style = MaterialTheme.typography.displayMedium)
    }

}
