package com.example.proyectofinalopentech.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.CollectionsBookmark
import androidx.compose.material.icons.filled.Explore
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationScreens(val route: Screen, val icon: ImageVector, val name:String) {
    object SavedPanels : BottomNavigationScreens(Screen.SavedPanelsScreen, Icons.Filled.Bookmark, "Saved panels")
    object MangaSearch : BottomNavigationScreens(Screen.MangaSearchScreen,  Icons.Filled.Explore,"Manga Search")
    object MyMangas : BottomNavigationScreens(Screen.MyMangasScreen,  Icons.Filled.CollectionsBookmark,"My mangas")
}