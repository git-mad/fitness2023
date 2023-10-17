package com.example.appprototype.ui


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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
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
import com.example.appprototype.R
import com.example.appprototype.ui.components.fontFamily

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
fun profileScreen() {
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
                    text = "Anne Joe",
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
                    text = "M T W T F",
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
                text = "Weightlifting",
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF49454F),
                    letterSpacing = 0.25.sp,
                ),
            )

            Text(
                text = "I am looking for someone to help keep me accountable and make sure I stay on track with my goals. Preferably someone who can workout between 10am-12pm on MWF",
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp),
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp)
            ) {
                Button(onClick = {
                }, modifier = Modifier
                    .width(80.dp)
                    .height(80.dp)
                    .background(Color.Transparent),
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    shape = RectangleShape
                ) {
                    Image(ImageVector.vectorResource(R.drawable.star), "Add to Favorites")
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