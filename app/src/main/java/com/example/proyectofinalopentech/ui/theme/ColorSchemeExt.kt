package com.example.proyectofinalopentech.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val ColorScheme.textFieldActiveBackground: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) GreyE0 else GreyE0

val ColorScheme.hintText: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) Grey6D else Grey6D

val ColorScheme.textColorOnBlack: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) White else White