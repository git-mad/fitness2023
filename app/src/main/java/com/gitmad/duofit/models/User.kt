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

        fun updateName(name: String) {
            synchronized(this) {
                instance?.profile = instance?.profile?.let {
                    Profile(
                        it.profileID, name, it.schedule, it.interests, it.mainGym,
                        it.description, it.isFavorite)
                }!!
            }
        }
        fun updateSchedule(schedule: String) {
            synchronized(this) {
                instance?.profile = instance?.profile?.let {
                    Profile(
                        it.profileID, it.name, schedule, it.interests, it.mainGym,
                        it.description, it.isFavorite)
                }!!
            }
        }

        fun updateInterests(interests: String) {
            synchronized(this) {
                instance?.profile = instance?.profile?.let {
                    Profile(
                        it.profileID, it.name, it.schedule, interests, it.mainGym,
                        it.description, it.isFavorite)
                }!!
            }
        }

        fun updateMainGym(mainGym: String) {
            synchronized(this) {
                instance?.profile = instance?.profile?.let {
                    Profile(
                        it.profileID, it.name, it.schedule, it.interests, mainGym,
                        it.description, it.isFavorite)
                }!!
            }
        }

        fun updateDescription(description: String) {
            synchronized(this) {
                instance?.profile = instance?.profile?.let {
                    Profile(
                        it.profileID, it.name, it.schedule, it.interests, it.mainGym,
                        description, it.isFavorite)
                }!!
            }
        }
    }
    fun getDetails() : Profile = profile

}