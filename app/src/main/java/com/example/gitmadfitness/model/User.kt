package com.example.gitmadfitness.model

data class User(var name: String, val email: String, val id: Int, val friends: List<Int>, val groups: List<Int>, val preferences: List<Preference>)