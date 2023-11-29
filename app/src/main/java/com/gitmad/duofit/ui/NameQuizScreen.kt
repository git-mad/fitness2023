package com.gitmad.duofit.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.gitmad.duofit.R
import com.gitmad.duofit.models.User
import com.gitmad.duofit.ui.components.QuizScreenTemplate
import com.gitmad.duofit.viewmodels.MainViewModel
import com.gitmad.duofit.viewmodels.Screen


@Composable
@Preview(showBackground = true)
fun NameQuizScreen(mainViewModel: MainViewModel = MainViewModel()) {
    QuizScreenTemplate(
        mainViewModel = mainViewModel,
        mainMessage = "Hello!",
        subMessage = "First, we need your name.",
        imagePainter = painterResource(R.drawable.mindfulness),
        textFieldPlaceholder = "Enter your name",
        progressBarNumber = 0,
        totalDots = 5,
        onContinueClicked = { string ->
            User.updateName(string)
            mainViewModel.navigateTo(Screen.ScheduleQuiz)
        }
    )
}


@Composable
@Preview(showBackground = true)
fun ScheduleQuizScreen(mainViewModel: MainViewModel = MainViewModel()) {
    QuizScreenTemplate(
        mainViewModel = mainViewModel,
        mainMessage = "Hi " + User.getInstance().getDetails().name + "!",
        subMessage = "Next, let us know when you work out each week.",
        imagePainter = painterResource(R.drawable.tracker),
        textFieldPlaceholder = "Enter your schedule",
        progressBarNumber = 1,
        totalDots = 5,
        onContinueClicked = { string ->
            User.updateSchedule(string)
            mainViewModel.navigateTo(Screen.InterestsQuiz)
        },
        onBackClicked ={ mainViewModel.navigateTo(Screen.NameQuiz) }
    )
}

@Composable
@Preview(showBackground = true)
fun InterestsQuizScreen(mainViewModel: MainViewModel = MainViewModel()) {
    QuizScreenTemplate(
        mainViewModel = mainViewModel,
        mainMessage = "What do you like to do?",
        subMessage = "Next, let us know your workout interests.",
        imagePainter = painterResource(R.drawable.hiking),
        textFieldPlaceholder = "Enter your interests",
        progressBarNumber = 2,
        totalDots = 5,
        onContinueClicked = { string ->
            User.updateInterests(string)
            mainViewModel.navigateTo(Screen.MainGymQuiz)
        },
        onBackClicked ={ mainViewModel.navigateTo(Screen.ScheduleQuiz) }
    )
}

@Composable
@Preview(showBackground = true)
fun MainGymQuizScreen(mainViewModel: MainViewModel = MainViewModel()) {
    QuizScreenTemplate(
        mainViewModel = mainViewModel,
        mainMessage = "Where do you exercise?",
        subMessage = "This can be a general area, a gym, or anywhere you exercise.",
        imagePainter = painterResource(R.drawable.personal_training),
        textFieldPlaceholder = "Enter your location",
        progressBarNumber = 3,
        totalDots = 5,
        onContinueClicked = { string ->
            User.updateMainGym(string)
            mainViewModel.navigateTo(Screen.DescriptionQuiz)
        },
        onBackClicked ={ mainViewModel.navigateTo(Screen.InterestsQuiz) }
    )
}

@Composable
@Preview(showBackground = true)
fun DescriptionQuizScreen(mainViewModel: MainViewModel = MainViewModel()) {
    QuizScreenTemplate(
        mainViewModel = mainViewModel,
        mainMessage = "Almost there!",
        subMessage = "Describe your fitness goals and what you hope to get out of this app!",
        imagePainter = painterResource(R.drawable.energizer),
        textFieldPlaceholder = "Enter your description",
        progressBarNumber = 4,
        totalDots = 5,
        onContinueClicked = { string ->
            User.updateDescription(string)
            mainViewModel.saveUserProfile()
            mainViewModel.navigateTo(Screen.Home)
        },
        onBackClicked ={
            mainViewModel.navigateTo(Screen.MainGymQuiz)
        }
    )
}