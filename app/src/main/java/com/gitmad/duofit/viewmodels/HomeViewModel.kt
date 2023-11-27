package com.gitmad.duofit.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gitmad.duofit.models.Profile
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _fetchedProfileList: MutableLiveData<MutableList<Profile>> =
        MutableLiveData<MutableList<Profile>>(
            mutableListOf()
        )
    val fetchedProfileList: LiveData<MutableList<Profile>> get() = _fetchedProfileList

    private val _viewedProfile = MutableLiveData<Profile>()
    val viewedProfile = _viewedProfile

    private val _showProfileSheet: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val showProfileSheet: LiveData<Boolean> = _showProfileSheet

    private val _listUpToDate: MutableLiveData<Boolean> = MutableLiveData(false)
    val listUpToDate: LiveData<Boolean> = _listUpToDate

    private val _isFetching: MutableLiveData<Boolean> = MutableLiveData(false)
    val isFetching: LiveData<Boolean> = _isFetching
    private fun addToList(profile: Profile) {
        val currentList = _fetchedProfileList.value ?: mutableListOf()
        currentList.add(profile)
        _fetchedProfileList.value = currentList
    }

    fun fetchList() {
        _isFetching.value = true
        //Routine to get profiles from the data base
        //yadda yadda yadda
        val profileList = listOf(
            Profile(
                profileID = "a1B2c34D",
                name = "James Anderson",
                schedule = "U M T W",
                interests = "Bodybuilding",
                mainGym = "Georgia Tech CRC",
                description = "Looking for a partner to motivate each other on Mondays. Prefer mornings.",
                isFavorite = false
            ),
            Profile(
                profileID = "5Ef6Gh78",
                name = "Lisa Matthews",
                schedule = "M W F S",
                interests = "Aerobics",
                mainGym = "Georgia Tech CRC",
                description = "Seeking someone energetic for aerobics. 3-5pm on MWF suits me best.",
                isFavorite = true
            ),
            Profile(
                profileID = "i9J0kLm1",
                name = "Robert Johnson",
                schedule = "T Th S",
                interests = "Weightlifting",
                mainGym = "Georgia Tech CRC",
                description = "Looking for a weightlifting buddy. Available Tuesdays and Saturdays after 6pm.",
                isFavorite = false
            ),
            Profile(
                profileID = "2nO3pQr4",
                name = "Jessica Turner",
                schedule = "M T Th F",
                interests = "Cardio",
                mainGym = "Georgia Tech CRC",
                description = "Need someone for morning runs. Let's chase our goals!",
                isFavorite = false
            ),
            Profile(
                profileID = "5sT6uVw7",
                name = "William Garcia",
                schedule = "U W F",
                interests = "Crossfit",
                mainGym = "Georgia Tech CRC",
                description = "I do Crossfit on weekdays. Hoping to find a partner for consistency.",
                isFavorite = false
            ),
            Profile(
                profileID = "x8Yz90Aa",
                name = "Emily White",
                schedule = "T Th F",
                interests = "Yoga",
                mainGym = "Georgia Tech CRC",
                description = "Yoga enthusiast! Looking for calmness and balance. Tuesdays and Fridays are perfect.",
                isFavorite = false
            ),
            Profile(
                profileID = "bBc2Dd3E",
                name = "David Martinez",
                schedule = "M T W",
                interests = "Martial Arts",
                mainGym = "Georgia Tech CRC",
                description = "Into martial arts. Seeking someone with similar interests. Mornings are best.",
                isFavorite = false
            ),
            Profile(
                profileID = "f4Gh5Ij6",
                name = "Sophia Smith",
                schedule = "W Th S",
                interests = "Pilates",
                mainGym = "Georgia Tech CRC",
                description = "Join me for pilates thrice a week. Preferably after 3pm.",
                isFavorite = false
            ),
            Profile(
                profileID = "k7Lm8No9",
                name = "John Brown",
                schedule = "U T F",
                interests = "Bodybuilding",
                mainGym = "Georgia Tech CRC",
                description = "Early bird here! Let's workout together before sunrise.",
                isFavorite = false
            ),
            Profile(
                profileID = "p0Q1r2S3",
                name = "Ava Rodriguez",
                schedule = "M T F S",
                interests = "Zumba",
                mainGym = "Georgia Tech CRC",
                description = "Love Zumba! Seeking enthusiastic partners for evenings.",
                isFavorite = true
            ),
            Profile(
                profileID = "t4U5vWx6",
                name = "Michael Wilson",
                schedule = "T W Th",
                interests = "Weightlifting",
                mainGym = "Georgia Tech CRC",
                description = "Strength training is my focus. Let's lift together!",
                isFavorite = false
            ),
            Profile(
                profileID = "y7Z8a9B0",
                name = "Mia King",
                schedule = "U T W F",
                interests = "Aerobics",
                mainGym = "Georgia Tech CRC",
                description = "Seeking high energy folks. I'm free on weekdays except Thursday.",
                isFavorite = false
            ),
            Profile(
                profileID = "cCd1E2f3",
                name = "Ethan Davis",
                schedule = "M W Th S",
                interests = "Cardio",
                mainGym = "Georgia Tech CRC",
                description = "If you love running, let's be partners. Early mornings or late evenings.",
                isFavorite = false
            ),
            Profile(
                profileID = "g4H5iJ6k",
                name = "Olivia Hernandez",
                schedule = "T W S",
                interests = "Crossfit",
                mainGym = "Georgia Tech CRC",
                description = "Crossfit thrice a week. Join me and let's stay fit!",
                isFavorite = false
            ),
            Profile(
                profileID = "l7M8nO9p",
                name = "Daniel Jones",
                schedule = "M Th F",
                interests = "Martial Arts",
                mainGym = "Georgia Tech CRC",
                description = "Seeking sparring partners. Best if available in the evenings.",
                isFavorite = true
            ),
            Profile(
                profileID = "q0R1s2T3",
                name = "Isabella Taylor",
                schedule = "U T F",
                interests = "Yoga",
                mainGym = "Georgia Tech CRC",
                description = "Peaceful yoga sessions wanted. Mornings are my go-to.",
                isFavorite = false
            ),
            Profile(
                profileID = "u4V5wX6y",
                name = "Alexander Lewis",
                schedule = "M W S",
                interests = "Bodybuilding",
                mainGym = "Georgia Tech CRC",
                description = "Building muscle together. Let's aim for consistency and gains!",
                isFavorite = false
            ),
            Profile(
                profileID = "z7A8bB9c",
                name = "Emma Martin",
                schedule = "T Th F S",
                interests = "Pilates",
                mainGym = "Georgia Tech CRC",
                description = "Love Pilates. Seeking someone to sync our routines.",
                isFavorite = false
            ),
            Profile(
                profileID = "d0E1f2G3",
                name = "Lucas Walker",
                schedule = "U M T",
                interests = "Weightlifting",
                mainGym = "Georgia Tech CRC",
                description = "Lifting is my passion. Need a spotter and friend for motivation!",
                isFavorite = false
            ),
            Profile(
                profileID = "h4I5jK6l",
                name = "Hannah Young",
                schedule = "W Th S",
                interests = "Aerobics",
                mainGym = "Georgia Tech CRC",
                description = "Aerobics thrice a week. Let's push each other to the next level!",
                isFavorite = false
            ),
        )

        profileList.forEach { profile ->
            addToList(profile)
            println("Adding ID:" + profile.profileID + "to profileList")
        }
        _listUpToDate.value = true
        _isFetching.value = false
    }

    fun resetList() {
        _fetchedProfileList.value = mutableListOf()
        _listUpToDate.value = false
    }

    fun hideSheet() {
        _viewedProfile.value = null
        _showProfileSheet.value = false
    }

    fun showSheet() {
        _showProfileSheet.value = true
    }

    fun updateFavoriteStatus(updatedProfile: Profile) {
        viewModelScope.launch {
            val updatedList = _fetchedProfileList.value?.map { profile ->
                if (profile.profileID == updatedProfile.profileID) {
                    updatedProfile
                } else {
                    profile
                }
            }
            _fetchedProfileList.postValue(updatedList as MutableList<Profile>?)
        }
    }
}