package com.gitmad.duofit.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gitmad.duofit.models.Profile
import com.gitmad.duofit.models.User
import com.gitmad.duofit.viewmodels.MainViewModel
import com.gitmad.duofit.viewmodels.Screen


@Composable
@Preview
fun ProfileQuizScreen(mainViewModel: MainViewModel = viewModel()) {
    var name by rememberSaveable { mutableStateOf(User.getInstance().getDetails().name) }
    var schedule by rememberSaveable { mutableStateOf(User.getInstance().getDetails().schedule) }
    var interests by rememberSaveable { mutableStateOf(User.getInstance().getDetails().interests) }
    var mainGym by rememberSaveable { mutableStateOf(User.getInstance().getDetails().mainGym) }
    var description by rememberSaveable { mutableStateOf(User.getInstance().getDetails().description) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = name,
            onValueChange = { name = it },
            label = { Text("Enter your name") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = schedule,
            onValueChange = { schedule = it },
            label = { Text("Enter your schedule (note format!)") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = interests,
            onValueChange = { interests = it },
            label = { Text("Enter your interests") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = mainGym,
            onValueChange = { mainGym = it },
            label = { Text("Enter your main gym") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = description,
            onValueChange = { description = it },
            label = { Text("Enter a description") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            User.updateProfile(Profile("123zjaf23", name, schedule,
                interests, mainGym, description))
            mainViewModel.navigateTo(Screen.Home)
        }) {
            Text("Next")
        }


    }
}

