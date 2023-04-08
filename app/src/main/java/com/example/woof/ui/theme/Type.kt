package com.example.woof.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.woof.R

val caveatFontFamily = FontFamily(
    Font(R.font.caveat_regular,FontWeight.Normal),
    Font(R.font.caveat_medium,FontWeight.Medium),
    Font(R.font.caveat_semibold,FontWeight.SemiBold),
    Font(R.font.caveat_bold,FontWeight.Bold)
)

val Typography = Typography(
    defaultFontFamily = caveatFontFamily,
    h1 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp
    ),
    h2 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    h3 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    body1 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),





)