package com.example.appprototype.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appprototype.ui.components.ProfileCard2

@Composable
fun HomeScreen(onNavigateToProfileScreen: () -> Unit){
    LazyColumn(modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(100.dp)
    ){
        item (){
            ProfileCard2(onNavigateToProfileScreen)
            ProfileCard2(onNavigateToProfileScreen)
            ProfileCard2(onNavigateToProfileScreen)
            ProfileCard2(onNavigateToProfileScreen)
            ProfileCard2(onNavigateToProfileScreen)
            ProfileCard2(onNavigateToProfileScreen)
        }
    }
}