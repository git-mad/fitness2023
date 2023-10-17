package com.example.appprototype.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun MessagesScreen (){
    Column(Modifier.padding(10.dp).fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "No Messages Available.", Modifier.align(Alignment.CenterHorizontally))
    }
}