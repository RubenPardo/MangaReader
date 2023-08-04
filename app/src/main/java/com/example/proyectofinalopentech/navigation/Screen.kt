package com.example.proyectofinalopentech.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(
    val route:String,
    val arguments: List<NamedNavArgument>
){


    object SavedPanelsScreen : Screen(
        route = "saved_panels",
        arguments = emptyList()
    )

    object MangaSearchScreen : Screen(
        route = "manga_search",
        arguments = emptyList()
    )
    object MyMangasScreen : Screen(
        route = "my_mangas",
        arguments = emptyList()
    )

    object MangaDetails : Screen(
        route = "manga_details",
        arguments = listOf(navArgument("manga_id") { type = NavType.StringType })
    )
}
