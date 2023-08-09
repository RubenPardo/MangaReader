package com.example.proyectofinalopentech.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.proyectofinalopentech.navigation.Screen

@Composable
fun HomeScreen(
    navController: NavController,
    changeScreen: (Screen) -> Unit
){

    Contenido()

}

@Composable
fun Contenido() {
    TODO("Not yet implemented")
}

@Preview
@Composable
fun Preview() {
    Contenido()
}
