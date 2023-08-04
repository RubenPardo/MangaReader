package com.example.proyectofinalopentech.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationGraph(navController:NavHostController){

    NavHost(
        navController = navController,
        startDestination = Screen.SavedPanelsScreen.route){
        addMangaSearchScreen(navController)
        addSavedPanelsScreen(navController)
        addMyMangasScreen(navController)
        addMangaDetails(navController)
    }
}