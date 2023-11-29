package com.gitmad.duofit.ui

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gitmad.duofit.viewmodels.MainViewModel

@Composable
@Preview
fun ScheduleInputScreen(mainViewModel: MainViewModel = viewModel()) {
    var scheduleState by remember { mutableStateOf("") }



    Button(onClick = { onScheduleSelected(scheduleState) }) {
        Text("Next")
    }
}

fun onScheduleSelected(scheduleState: Any) {
    TODO("Not yet implemented")
}
