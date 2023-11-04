package com.example.appprototype.models


data class Profile (
    val profileID: String = "-1",
    var name: String = "N/A",
    var schedule: String = "N/A",
    var interests: String = "N/A",
    var mainGym: String = "N/A",
    var description: String = "N/A",
    var isFavorite: Boolean = false)