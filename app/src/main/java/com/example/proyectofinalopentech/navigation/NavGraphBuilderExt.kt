package com.example.proyectofinalopentech.navigation

import androidx.compose.foundation.lazy.LazyListState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.proyectofinalopentech.presentation.screens.MangaDetails
import com.example.proyectofinalopentech.presentation.screens.MangaSearchScreen
import com.example.proyectofinalopentech.presentation.screens.MyMangasScreen
import com.example.proyectofinalopentech.presentation.screens.SavedPanelsScreen


fun NavGraphBuilder.addMangaSearchScreen(navController: NavController, scrollState: LazyListState){
    composable(Screen.MangaSearchScreen.route){
        MangaSearchScreen(scrollState) { navController.navigate(Screen.MangaDetails.route + "/$it") }
    }
}

fun NavGraphBuilder.addSavedPanelsScreen(navController: NavHostController) {
    composable(Screen.SavedPanelsScreen.route){
        SavedPanelsScreen()
    }
}

fun NavGraphBuilder.addMyMangasScreen(navController: NavHostController,scrollState: LazyListState) {
    composable(Screen.MyMangasScreen.route){
        MyMangasScreen(scrollState) { navController.navigate(Screen.MangaDetails.route + "/$it") }
    }
}

fun NavGraphBuilder.addMangaDetails(navController: NavHostController) {
        composable(
            route = Screen.MangaDetails.route + "/{manga_id}",
            arguments = Screen.MangaDetails.arguments)
        {
            val id = it.arguments?.getString("manga_id") ?: ""
            MangaDetails(
                mangaId = id,
                goBack = {if (navController.previousBackStackEntry != null) { navController.navigateUp() } else null}
            )
        }
}
