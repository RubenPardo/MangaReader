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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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

@Composable
fun BottomNav(
    callback: (Int) -> Unit,
    boxScope: BoxScope,
    hide: Boolean = false
){
    var currentIndex by remember { mutableStateOf(1) }
    if(!hide) boxScope.apply {
        Box(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter)
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

                    BottomIcon(
                        icon = {Icon(
                            modifier = Modifier.size(if(currentIndex == 1) 32.dp else 24.dp),
                            imageVector = Icons.Filled.Bookmark,
                            contentDescription = "Back",
                            tint = if(currentIndex == 1) Color.Black else Color.Gray
                        )},{
                            currentIndex = 1
                            callback.invoke(1)
                        })
                    BottomIcon(
                         icon = {
                            Icon(
                                imageVector = Icons.Filled.Explore,
                                modifier = Modifier.size(if(currentIndex == 2) 32.dp else 24.dp),
                                contentDescription = "Back",
                                tint = if(currentIndex == 2) Color.Black else Color.Gray

                            )
                        }, {
                            currentIndex = 2
                            callback.invoke(2)
                        }
                    )
                    BottomIcon(icon = {
                            Icon(
                                imageVector = Icons.Filled.Book,
                                modifier = Modifier.size(if(currentIndex == 3) 32.dp else 24.dp),
                                contentDescription = "Back",
                                tint = if(currentIndex == 3) Color.Black else Color.Gray
                            )
                        }, {
                            currentIndex = 3
                            callback.invoke(3)
                        },
                    )
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


