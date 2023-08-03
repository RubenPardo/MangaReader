package com.example.proyectofinalopentech.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.proyectofinalopentech.presentation.common.BottomNav
import com.example.proyectofinalopentech.presentation.common.CustomTopAppBar
import com.example.proyectofinalopentech.presentation.mangalist.MangaList
import com.example.proyectofinalopentech.ui.theme.ProyectoFinalOpenTechTheme

class MainActivity(): ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProyectoFinalOpenTechTheme() {
                var mangaName by remember { mutableStateOf("") }
                var isScrollingUp by remember { mutableStateOf(false) }
                Scaffold(
                    topBar = { CustomTopAppBar(title = "Browse", backCallback = null) },
                ){ innerPadding ->
                    Box {

                        Column {
                            Spacer(modifier = Modifier.height(innerPadding.calculateTopPadding()))
                            SearchBarComponent(
                                hint = "Search Manga"
                            ){ value ->
                                mangaName = value
                            }
                            // GenreFilterComponent()

                            MangaList(mangaName = mangaName){ value ->
                                isScrollingUp = value
                            }
                        }
                        BottomNav(callback = { println(it) },this, hide = isScrollingUp)
                    }

                }
            }
        }
    }
}



