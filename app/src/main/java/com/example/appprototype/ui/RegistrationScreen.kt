package com.example.appprototype.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appprototype.R
import com.example.appprototype.viewmodels.MainViewModel

@Composable
@Preview
fun RegistrationScreen(mainViewModel: MainViewModel = viewModel()) {
    var medicationName by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(16.dp, 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text(
            text = "Registration",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.displaySmall
        )

        Spacer(modifier = Modifier.padding(8.dp))

        Text(
            text = "Email",
            style = MaterialTheme.typography.bodyLarge
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = medicationName,
            onValueChange = { medicationName = it },
            placeholder = { Text(text = "email@address.com") },
        )

        Spacer(modifier = Modifier.padding(8.dp))

        Text(
            text = "Password",
            style = MaterialTheme.typography.bodyLarge
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = medicationName,
            onValueChange = { medicationName = it },
            placeholder = { Text(text = "email@address.com") },
        )

        Spacer(modifier = Modifier.padding(8.dp))

        Text(
            text = "Confirm Password",
            style = MaterialTheme.typography.bodyLarge
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = medicationName,
            onValueChange = { medicationName = it },
            placeholder = { Text(text = "email@address.com") },
        )

        Spacer(modifier = Modifier.padding(8.dp))


    }
}