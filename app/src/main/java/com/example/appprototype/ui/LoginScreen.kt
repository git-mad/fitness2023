package com.example.appprototype.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appprototype.viewmodels.MainViewModel
import com.example.appprototype.viewmodels.Screen

@Composable
@Preview
fun LoginScreen(mainViewModel: MainViewModel = viewModel()) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        // Username field, password field, etc.

        TextField(
            value = "my@mail.com", // TODO:
            onValueChange = { it },
            label = { Text("Email") },
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = "*****",
            onValueChange = { it },
            label = { Text("Password") },
        )

        Button(onClick = { mainViewModel.navigateTo(Screen.Registration) }) {
            Text("Log in")
        }
    }
}

@Composable
fun RegistrationScreen(mainViewModel: MainViewModel) {
    // Create your registration form here
}
