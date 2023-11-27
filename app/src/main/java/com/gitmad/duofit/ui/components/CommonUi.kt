package com.gitmad.duofit.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gitmad.duofit.R
import com.gitmad.duofit.models.Profile

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val fontName = GoogleFont("Roboto")

val fontFamily = FontFamily(
    Font(googleFont = fontName, fontProvider = provider)
)

@Composable
fun profileCard(onClick: () -> Unit, profile: Profile){
    Column(
        verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .height(104.dp)
            .clickable(onClick = onClick)
            .background(Color.White)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .fillMaxWidth()
                .height(104.dp)
                .padding(start = 16.dp, top = 12.dp, end = 24.dp, bottom = 12.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .width(80.dp)
                    .height(80.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier
                        .width(80.dp)
                        .height(80.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.default_profile_icon),
                        contentDescription = "User Icon",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .padding(0.dp)
                            .width(80.dp)
                            .height(80.dp)
                            .clip(CircleShape)
                    )
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .width(248.dp)
                    .height(80.dp)
            ) {
                Text(
                    text = profile.schedule,
                    style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF49454F),
                        letterSpacing = 0.5.sp,
                    ),
                    modifier = Modifier
                        .width(248.dp)
                        .height(14.dp)
                )
                Text(
                    text = profile.name,
                    style = TextStyle(
                        fontSize = 28.sp,
                        lineHeight = 32.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF191C1D),

                        letterSpacing = 0.5.sp,
                    ),
                    modifier = Modifier
                        .width(248.dp)
                        .height(36.dp)
                )
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
                    modifier = Modifier
                        .width(248.dp)
                        .height(40.dp)
                )
            }
        }
    }
}