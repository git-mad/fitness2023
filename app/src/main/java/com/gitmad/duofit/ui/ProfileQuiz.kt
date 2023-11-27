package com.gitmad.duofit.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gitmad.duofit.viewmodels.MainViewModel

@Preview
@Composable
fun ProfileQuizScreen(mainViewModel: MainViewModel = viewModel()) {
    Text("afds")
    // Create your quiz form here
    // Once completed, you can call a function in the ViewModel to save the user profile
}
