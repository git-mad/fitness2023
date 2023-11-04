package com.example.appprototype.viewmodels

import android.service.autofill.UserData
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appprototype.models.Profile
import com.example.appprototype.models.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


sealed class Screen(val route: String, val friendlyName : String) {
    object Home : Screen("home", "Home")
    object Settings : Screen("settings", "Settings")

    object Messages : Screen("messages", "Messages")

    object Favorites : Screen("favorites", "Favorites")

    object Login : Screen("login", "Login")

    object Registration : Screen("registration", "Register")
    object ProfileQuiz : Screen("profileQuiz", "Profile Quiz")
    object Splash : Screen("splash", "Splash")
}

class MainViewModel : ViewModel() {
    init {
        User.initializeInstance(Profile(profileID = "1", name = "Michael Robinson", schedule = "M W F", interests = "Bodybuilding", mainGym = "Georgia Tech Campus Recreational Center (CRC)", description = "Hello World"))
    }
    // LiveData or MutableState that holds the current Screen
    private val _currentScreen = MutableLiveData<Screen>(Screen.Splash)
    val currentScreen: LiveData<Screen> = _currentScreen
    private val _showFAB = MutableLiveData(currentScreen.value == Screen.Home)
    val showFab: LiveData<Boolean> = _showFAB
    private val _user = MutableLiveData(User.getInstance())
    val user: LiveData<User> = _user


    fun navigateTo(screen: Screen) {
        _currentScreen.value = screen
        _showFAB.value = currentScreen.value == Screen.Home
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

    fun saveUserProfile(profile: Profile) {
        // TODO: Implement profile save logic
        // On success, navigate to Home
        navigateTo(Screen.Home)
    }
}