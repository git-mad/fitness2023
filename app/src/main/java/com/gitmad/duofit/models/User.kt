package com.gitmad.duofit.models

class User private constructor(private var profile: Profile){
    companion object {
        @Volatile
        private var instance: User? = null

        fun initializeInstance(profile: Profile) {
            if (instance != null) {
                throw IllegalStateException("Instance already initialized")
            }

            synchronized(this) {
                if (instance == null) {
                    instance = User(profile)
                }
            }
        }
        fun getInstance(): User =
            instance ?: throw IllegalStateException("Instance not initialized")

        fun updateProfile(newProfile: Profile) {
            synchronized(this) {
                instance?.profile = newProfile
            }
        }
    }
    fun getDetails() : Profile = profile

}