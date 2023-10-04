package com.example.appprototype.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appprototype.R

@Composable
fun ProfileCard(name: String, workoutType: String, weekdays: String){
    ElevatedCard(elevation = CardDefaults.cardElevation(
        defaultElevation = 6.dp
    ),
        modifier = Modifier
            .size(LocalConfiguration.current.screenWidthDp.dp - 20.dp, 160.dp)
            .clickable {}
            .padding(vertical = 8.dp)){
        Row(modifier = Modifier
            .padding(start = 10.dp,
                top = 20.dp,
                bottom = 20.dp,
                end = 10.dp
            )
        ){
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp, 100.dp)
                    .align(Alignment.CenterVertically)
                    .clip(CircleShape)
            )
            Column (modifier = Modifier
            ) {
                Text(
                    name,
                    modifier = Modifier
                        .padding(
                            start = 10.dp,
                            top = 4.dp,
                            bottom = 4.dp,
                            end = 10.dp
                        ),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
                Text(
                    workoutType,
                    modifier = Modifier
                        .padding(
                            start = 10.dp,
                            top = 4.dp,
                            bottom = 4.dp,
                            end = 10.dp
                        ),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
                Text(
                    weekdays,
                    modifier = Modifier
                        .padding(
                            start = 10.dp,
                            top = 4.dp,
                            bottom = 4.dp,
                            end = 10.dp
                        ),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
            }
        }

    }
}