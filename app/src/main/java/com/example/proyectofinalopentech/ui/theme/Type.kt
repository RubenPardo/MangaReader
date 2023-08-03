package com.example.proyectofinalopentech.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    titleLarge = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.W900,
        fontSize = 24.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),

    titleMedium = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
)

val Typography.searchHint: TextStyle
    @Composable
    get() = TextStyle(
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.hintText,
            fontSize = 18.sp
        )

val Typography.primaryButton: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Monospace,
        color = MaterialTheme.colorScheme.textColorOnBlack
    )

val Typography.titleMangaItem: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 24.sp,
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Bold
    )

val Typography.subtitleSmall: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 16.sp,
        fontFamily = FontFamily.Monospace,
        color = Color.Gray,
        fontWeight = FontWeight.Bold
    )

val Typography.subtitleLarge: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 20.sp,
        color = Color.Black,
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Bold
    )



val Typography.textFieldText: TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.hintText,
        fontSize = 16.sp
    )