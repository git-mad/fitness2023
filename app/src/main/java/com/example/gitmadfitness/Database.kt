package com.example.gitmadfitness

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Database {
    private val db: DatabaseReference = Firebase.database.reference


    companion object {
        val USERS_CHILD = "users"
        val GROUPS_CHILD = "groups"
    }
}