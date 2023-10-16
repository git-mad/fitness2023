package com.example.appprototype.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


sealed class Screen(val route: String, val friendlyName : String) {
    object Home : Screen("home", "Home")
    object Settings : Screen("settings", "Settings")

    object Messages : Screen("messages", "Messages")

    object Favorites : Screen("Favorites", "Favorites")
}

class MainViewModel : ViewModel() {
    // LiveData or MutableState that holds the current Screen
    private val _currentScreen = MutableLiveData<Screen>(Screen.Home)
    val currentScreen: LiveData<Screen> = _currentScreen

    // Navigate to a specific screen
    fun navigateTo(screen: Screen) {
        _currentScreen.value = screen
    }

    // Additional navigation logic as per the requirements...
}