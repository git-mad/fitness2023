package com.gitmad.duofit.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gitmad.duofit.R
import com.gitmad.duofit.models.Profile
import com.gitmad.duofit.ui.components.fontFamily

@Composable
fun ProfileImage(resID: Int) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
    ){
        Image(
            painter = painterResource(resID),
            contentDescription = "Profile Image",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth()
                .weight(1f)
                .aspectRatio(1f)
        )
    }
}

@Composable
@Preview
fun ProfileScreen(
    profile: Profile = Profile(),
) {
    var isStarred by remember { mutableStateOf(false) }
    isStarred = profile.isFavorite
    Column {
        ProfileImage(resID = R.drawable.default_profile_icon)
        Column (
            modifier = Modifier
                .padding(10.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = profile.name,
                    style = TextStyle(
                        fontSize = 28.sp,
                        lineHeight = 28.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF191C1D),

                        letterSpacing = 0.5.sp,
                    ),
                    modifier = Modifier
                        .padding(end = 10.dp)
                )
                Text(
                    text = profile.schedule,
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 20.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF49454F),
                        letterSpacing = 0.5.sp,
                    ),
                )
            }
            Text(
                text = profile.interests,
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF49454F),
                    letterSpacing = 0.25.sp,
                ),
            )

            Row(Modifier.fillMaxWidth()
                .padding(top = 5.dp, bottom = 5.dp)){
                Image(
                    Icons.Filled.LocationOn,
                    contentDescription = "Location"
                )
                Text(
                    text = profile.mainGym,
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 20.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF49454F),
                        letterSpacing = 0.25.sp,
                    ),
                )
            }

            Text(
                text = profile.description,
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp),
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp)
            ) {
                Button(onClick = {
                    profile.isFavorite = !profile.isFavorite
                    isStarred = profile.isFavorite
                }, modifier = Modifier
                    .width(80.dp)
                    .height(80.dp)
                    .background(Color.Transparent),
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    shape = RectangleShape
                ) {
                    Image(ImageVector.vectorResource(if (isStarred) R.drawable.pinkstar else R.drawable.star),
                        "Add to Favorites")
                }
                Spacer( modifier = Modifier.weight(1f,true))
                Button(onClick = {

                }, modifier = Modifier
                    .width(80.dp)
                    .height(80.dp)
                    .background(Color.Transparent),
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    shape = RectangleShape
                ) {
                    Image(ImageVector.vectorResource(R.drawable.baseline_chat_96), "Message")
                }
            }
        }

    }
}