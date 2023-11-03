package com.example.appprototype.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appprototype.viewmodels.MainViewModel
import com.example.appprototype.viewmodels.Screen

@Composable
fun LoginScreen(mainViewModel: MainViewModel) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        // Username field, password field, etc.

        Button(onClick = { /* TODO: Handle login */ }) {
            Text("Log in")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { mainViewModel.navigateTo(Screen.Registration) }) {
            Text("Register")
        }
    }
}

@Composable
fun RegistrationScreen(mainViewModel: MainViewModel) {
    // Create your registration form here
}
