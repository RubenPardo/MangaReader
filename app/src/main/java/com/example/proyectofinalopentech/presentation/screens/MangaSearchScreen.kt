package com.example.proyectofinalopentech.presentation.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.proyectofinalopentech.presentation.SearchBarComponent
import com.example.proyectofinalopentech.presentation.common.isScrollingUp
import com.example.proyectofinalopentech.presentation.mangalist.MangaList

@Composable
fun MangaSearchScreen(
    scrollState: LazyListState,
    gotToMangaDetails: (mangaId:String) -> Unit) {

    var mangaName by remember { mutableStateOf("") }
    Column {

        AnimatedVisibility(scrollState.isScrollingUp()){
            SearchBarComponent(
                hint = "Search Manga",
                initValue = mangaName
            ){ value ->
                mangaName = value
            }
        }
        // GenreFilterComponent()

        MangaList(mangaName = mangaName,gotToMangaDetails, scrollState)
    }
}