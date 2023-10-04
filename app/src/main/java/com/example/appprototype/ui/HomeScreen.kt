package com.example.appprototype.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appprototype.ui.components.ProfileCard

@Composable
fun homePage(){
    LazyColumn(modifier = Modifier
        .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(100.dp)
    ){
        item (){
            ProfileCard("Johnny Appleseed", "Cardio", "M W F")
            ProfileCard("John Doe", "Bodybuilding", "U T Th S")
            ProfileCard("Jane Doe", "Calisthenics", "T T S")
            ProfileCard("Alice Johnson", "Yoga", "M W F")
            ProfileCard("Bob Smith", "Pilates", "T Th S")
            ProfileCard("Cathy Williams", "Aerobics", "M T W")
            ProfileCard("David Lee", "Martial Arts", "M W F")
            ProfileCard("Eva Rodriguez", "Cycling", "T Th S")
            ProfileCard("Frank Murphy", "Running", "W F S")
            ProfileCard("Grace Kim", "Swimming", "M T W")
            ProfileCard("Hank Green", "Weightlifting", "U T Th S")
        }
    }
}