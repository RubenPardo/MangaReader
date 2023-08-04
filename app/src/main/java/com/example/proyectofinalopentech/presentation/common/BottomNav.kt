package com.example.proyectofinalopentech.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.proyectofinalopentech.navigation.BottomNavigationScreens
import com.example.proyectofinalopentech.navigation.Screen

@Composable
fun BottomNav(
    callback: (Screen) -> Unit,
    items: List<BottomNavigationScreens>,
    navController: NavController,
    hide: Boolean = false
){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    if(!hide)  {
        Box(modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 32.dp, vertical = 32.dp)
        ){
                Card(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 10.dp
                    )) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(16.dp))
                        .background(Color.White)
                        .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        items.forEachIndexed { _, item->
                            val isSelected = currentRoute == item.route.route
                            BottomIcon(
                                icon = {Icon(
                                    modifier = Modifier.size(if(isSelected) 32.dp else 24.dp),
                                    imageVector = item.icon,
                                    contentDescription = item.name,
                                    tint = if(isSelected) Color.Black else Color.Gray
                                )},{
                                    callback.invoke(item.route)
                                })
                        }
                    }
                }
        }
    }

}

@Composable
fun BottomIcon( icon: @Composable () -> Unit, callback: () -> Unit) {
    IconButton(
        modifier = Modifier.padding(8.dp),
        onClick = { callback.invoke() },)
    {
        icon.invoke()
    }
}


