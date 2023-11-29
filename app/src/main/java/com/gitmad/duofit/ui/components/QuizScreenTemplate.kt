package com.gitmad.duofit.ui.components

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gitmad.duofit.R
import com.gitmad.duofit.viewmodels.MainViewModel
import com.gitmad.duofit.viewmodels.Screen

@Composable

fun QuizScreenTemplate(
    mainViewModel: MainViewModel,
    mainMessage: String,
    subMessage: String,
    imagePainter: Painter,
    textFieldPlaceholder: String,
    progressBarNumber: Int,
    totalDots: Int = 5,
    onContinueClicked: (String) -> Unit,
    onBackClicked: (() -> Unit)? = null
) {
    var textFieldValue by rememberSaveable { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        // Only show the back button if there's a callback provided
        onBackClicked?.let { onBack ->
            IconButton(
                onClick = onBack,
                modifier = Modifier.align(Alignment.TopStart).padding(16.dp)
            ) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = mainMessage,
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
        Text(
            text = subMessage,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(Modifier.height(16.dp))

        Image(
            painter = imagePainter,
            contentDescription = "Quiz Image",
            modifier = Modifier.size(300.dp)
        )

        Spacer(Modifier.height(16.dp))

        TextField(
            value = textFieldValue,
            onValueChange = { textFieldValue = it },
            label = { Text(textFieldPlaceholder) }
        )

        Spacer(Modifier.height(16.dp))

        // Progress Indicator
        ProgressDots(totalDots = totalDots, currentDot = progressBarNumber)

        Button(
            onClick = { onContinueClicked(textFieldValue) },
            enabled = textFieldValue.isNotBlank()
        ) {
            Text("Continue")
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

@Preview(showBackground = true)
@Composable
fun QuizScreenTemplatePreview() {
    QuizScreenTemplate(
        mainViewModel = MainViewModel(),
        mainMessage = "Hello!",
        subMessage = "First, we need your name.",
        imagePainter = painterResource(R.drawable.mindfulness),
        textFieldPlaceholder = "Enter your name",
        progressBarNumber = 0,
        totalDots = 5,
        onContinueClicked = {},
        onBackClicked = { MainViewModel().navigateTo(Screen.NameQuiz) }
    )
}