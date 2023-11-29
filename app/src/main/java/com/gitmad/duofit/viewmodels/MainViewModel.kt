package com.gitmad.duofit.viewmodels

import android.service.autofill.UserData
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gitmad.duofit.models.Profile
import com.gitmad.duofit.models.User
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.installations.RandomFidGenerator
import kotlin.random.Random


sealed class Screen(val route: String, val friendlyName : String) {
    object Home : Screen("home", "Home")
    object Settings : Screen("settings", "Settings")

    object Messages : Screen("messages", "Messages")

    object Favorites : Screen("favorites", "Favorites")

    object Login : Screen("login", "Login")

    object Registration : Screen("registration", "Register")
    object ProfileQuiz : Screen("profileQuiz", "Profile Quiz")
    object Splash : Screen("splash", "Splash")
    object NameQuiz : Screen("nameQuiz", "Name Quiz")

    object ScheduleQuiz : Screen("scheduleQuiz", "Schedule Quiz")
    object InterestsQuiz : Screen("interestsQuiz", "Interests Quiz")
    object MainGymQuiz : Screen("mainGymQuiz", "Main Gym Quiz")
    object DescriptionQuiz : Screen("descriptionQuiz", "Description Quiz")

}

class MainViewModel : ViewModel() {
    init {
        User.initializeInstance(Profile(profileID = "1", name = "Michael Robinson", schedule = "M W F", interests = "Bodybuilding", mainGym = "Georgia Tech Campus Recreational Center (CRC)", description = "Hello World"))
    }
    // LiveData or MutableState that holds the current Screen
    private val _currentScreen = MutableLiveData<Screen>(Screen.Login)
    val currentScreen: LiveData<Screen> = _currentScreen
    private val _showFAB = MutableLiveData(currentScreen.value == Screen.Home)
    val showFab: LiveData<Boolean> = _showFAB
    private val _user = MutableLiveData(User.getInstance())
    val user: LiveData<User> = _user


    fun navigateTo(screen: Screen) {
        _currentScreen.value = screen
        _showFAB.value = currentScreen.value == Screen.Home
        Log.d("INFO", "launched screen")
    }

    // You would need functions to handle login, registration, and profile update.
    fun login(username: String, password: String) {
        // TODO: Implement login logic
        // On success, navigate to Home
        navigateTo(Screen.Home)
    }

    fun register(userData: UserData) {
        // TODO: Implement registration logic
        // On success, navigate to ProfileQuiz
        navigateTo(Screen.ProfileQuiz)
    }

    fun saveUserProfile() {
        val db = Firebase.firestore
        val random = RandomFidGenerator()

        val user = User.getInstance().getDetails()
        user?.let {
            val userProfile = hashMapOf(
                "name" to it.name,
                "schedule" to it.schedule,
                "description" to it.description,
                "interests" to it.interests,
                "mainGym" to it.mainGym
            )

            db.collection("users").document(random.createRandomFid())
                .set(userProfile)
                .addOnSuccessListener { Log.d("INFO", "add to firebase success") }
                .addOnFailureListener { Log.d("INFO", "add to firebase failure") }
        }
    }
}