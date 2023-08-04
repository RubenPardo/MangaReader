package com.example.proyectofinalopentech.navigation

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun NavigationGraph(navController: NavHostController, scrollState: LazyListState){

    NavHost(
        navController = navController,
        startDestination = Screen.SavedPanelsScreen.route){
        addMangaSearchScreen(navController,scrollState)
        addSavedPanelsScreen(navController)
        addMyMangasScreen(navController)
        addMangaDetails(navController)
    }
}