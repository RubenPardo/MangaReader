package com.example.proyectofinalopentech.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.proyectofinalopentech.R
import com.example.proyectofinalopentech.navigation.BottomNavigationScreens
import com.example.proyectofinalopentech.navigation.NavigationGraph
import com.example.proyectofinalopentech.navigation.Screen
import com.example.proyectofinalopentech.presentation.common.BottomNav
import com.example.proyectofinalopentech.presentation.common.CustomTopAppBar
import com.example.proyectofinalopentech.presentation.common.isScrollingUp
import com.example.proyectofinalopentech.ui.theme.ProyectoFinalOpenTechTheme

class MainActivity(): ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProyectoFinalOpenTechTheme() {
                // State of bottomBar, set state to false, if current page route is "car_details"
                val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
                // State of topBar, set state to false, if current page route is "car_details"
                val topBarStateShowBack = rememberSaveable { (mutableStateOf(true)) }

                val navController = rememberNavController()
                val scrollState = rememberLazyListState()
                var showTopBar  = scrollState.isScrollingUp()

                // Subscribe to navBackStackEntry, required to get current route
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                // Control TopBar and BottomBar
                when (currentRoute) {
                    Screen.HomeScreen.route -> {
                        showTopBar = false
                        bottomBarState.value = false
                    }
                    Screen.MangaDetails.route+"/{manga_id}" -> {
                        bottomBarState.value = false
                        topBarStateShowBack.value = true
                    }
                    Screen.ChapterDetails.route+"/{chapter_id}/{page}" -> {
                        bottomBarState.value = false
                        topBarStateShowBack.value = false
                        showTopBar = false
                    }
                    else -> {
                        bottomBarState.value = true
                        topBarStateShowBack.value = false

                    }
                }

                val titleTopBar = getTitleTopBar(currentRoute)

                Scaffold(
                    bottomBar = { if(bottomBarState.value) BuildNavBar(
                        scrollState.isScrollingUp(),
                        navController
                    )},
                    topBar = { BuildTopBar(titleTopBar,topBarStateShowBack.value, showTopBar,navController) },
                ){ innerPadding ->
                    Box {
                        Box(modifier = Modifier.padding(top = innerPadding.calculateTopPadding())){
                            NavigationGraph(navController,scrollState)
                        }
                    }

                }
            }
        }
    }

    @Composable
    private fun BuildTopBar(
        titleTopBar: String,
        showBackButton: Boolean,
        show: Boolean,
        navController: NavHostController
    ) {
        if(show) {
            CustomTopAppBar(
                title = titleTopBar,
                backCallback = if(showBackButton){{navController.navigateUp()}}else null
            )
        }

    }

    @Composable
    private fun getTitleTopBar(currentRoute: String?): String {
        var res = ""
        when(currentRoute){
            Screen.MangaDetails.route+"/{manga_id}" -> res = stringResource(R.string.manga_details)
            Screen.MangaSearchScreen.route ->  res = stringResource(R.string.browse)
            Screen.MyMangasScreen.route -> res = stringResource(R.string.my_mangas)
            Screen.SavedPanelsScreen.route -> res = stringResource(R.string.saved_panels)
            else -> {}
        }

        return res
    }

    @Composable
    private fun BuildNavBar(show: Boolean, navController: NavHostController) {

       return AnimatedVisibility(
           visible = show,
           enter = slideInVertically(initialOffsetY = { it / 2 }),
           exit = slideOutVertically(targetOffsetY = { it }),
       ) {

           BottomNav(
               callback = { screen->
                   navController.navigate(screen.route) {

                       navController.graph.startDestinationRoute?.let { screen_route ->
                           popUpTo(screen_route) {
                               saveState = true
                               //inclusive = true
                           }
                       }
                       //launchSingleTop = true
                       restoreState = true
                   }},
               listOf(
                   BottomNavigationScreens.MangaSearch,
                   BottomNavigationScreens.MyMangas,
                   BottomNavigationScreens.SavedPanels,
               ),
               navController = navController,
               hide = false)

       }
    }
}



