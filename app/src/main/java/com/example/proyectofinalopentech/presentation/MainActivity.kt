package com.example.proyectofinalopentech.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.proyectofinalopentech.navigation.BottomNavigationScreens
import com.example.proyectofinalopentech.navigation.NavigationGraph
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
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = { BuildNavBar(navController)},
                    topBar = { CustomTopAppBar(title = "Browse", backCallback = null) },
                ){ innerPadding ->
                    Box {
                        Box(modifier = Modifier.padding(top = innerPadding.calculateTopPadding())){
                            NavigationGraph(navController)
                        }
                    }

                }
            }
        }
    }

    @Composable
    private fun BuildNavBar(navController: NavHostController) {
       return BottomNav(
            callback = { screen->
                navController.navigate(screen.route) {

                    navController.graph.startDestinationRoute?.let { screen_route ->
                        popUpTo(screen_route) {
                            saveState = true
                            inclusive = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                }},
            listOf(
                BottomNavigationScreens.SavedPanels,
                BottomNavigationScreens.MangaSearch,
                BottomNavigationScreens.MyMangas,
            ),
            navController = navController,
            hide = false)
    }
}



