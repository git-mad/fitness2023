package com.gitmad.duofit.ui

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gitmad.duofit.R
import com.gitmad.duofit.viewmodels.MainViewModel
import com.gitmad.duofit.viewmodels.Screen


@Composable
@Preview(showBackground = true)
fun LoginScreen(mainViewModel: MainViewModel = MainViewModel()) {
    var name by rememberSaveable { mutableStateOf("") }
    var image by rememberSaveable { mutableStateOf<Bitmap?>(null) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome to DuoFit",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Connect with fitness enthusiasts and find your perfect workout partner.",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )


        Image(
            painter = painterResource(R.drawable.trainer),
            contentDescription = "Trainer",
            modifier = Modifier.size(300.dp)
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = { mainViewModel.navigateTo(Screen.NameQuiz)
            },
        ) {
            Text("Google Login")
        }
    }
}

@Composable
fun ProgressDots(totalDots: Int, currentDot: Int) {
    Row(
        modifier = Modifier
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        for (i in 0 until totalDots) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(if (i == currentDot) Color.Gray else Color.LightGray)
            )
        }
    }
}