package com.gitmad.duofit.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gitmad.duofit.models.Profile
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
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

        _isFetching.value = true

        val profileList = mutableListOf<Profile>()
        val db = Firebase.firestore
        db.collection("users").get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val profile = Profile(
                        profileID = document.id, // Use the document ID as the profile ID
                        name = document.getString("name") ?: "N/A",
                        schedule = document.getString("schedule") ?: "N/A",
                        interests = document.getString("interests") ?: "N/A",
                        mainGym = document.getString("mainGym") ?: "N/A",
                        description = document.getString("description") ?: "N/A",
                        isFavorite = false // Set isFavorite to false
                    )
                    Log.d("INFO", document.id)
                    profileList.add(profile)
                }
                Log.d("INFO", "fetching success")
                _fetchedProfileList.value = profileList
                _listUpToDate.value = true
                _isFetching.value = false
            }
            .addOnFailureListener { exception ->
                Log.d("INFO", "fetching failed")
                _isFetching.value = false
            }


        profileList.forEach { profile ->
            addToList(profile)
            Log.d("INFO","Adding ID:" + profile.profileID + "to profileList")
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

}