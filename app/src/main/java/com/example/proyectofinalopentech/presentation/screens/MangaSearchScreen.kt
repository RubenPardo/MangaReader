package com.example.proyectofinalopentech.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.example.proyectofinalopentech.presentation.SearchBarComponent
import com.example.proyectofinalopentech.presentation.mangalist.MangaList

@Composable
fun MangaSearchScreen(gotToMangaDetails: (mangaId:String) -> Unit) {
    var mangaName by remember { mutableStateOf("") }
    var isScrollingUp by remember { mutableStateOf(false) }
    Column {
        SearchBarComponent(
            hint = "Search Manga"
        ){ value ->
            mangaName = value
        }
        // GenreFilterComponent()

        MangaList(mangaName = mangaName,gotToMangaDetails){ value ->
            isScrollingUp = value
        }
    }
}