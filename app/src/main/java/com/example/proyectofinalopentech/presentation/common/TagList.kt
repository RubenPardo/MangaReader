package com.example.proyectofinalopentech.presentation.common

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagList(tags: List<String>, maxItems: Int? = null) {
    FlowRow(
        maxItemsInEachRow = 5,
    ) {

       var items = if(maxItems!=null){
            if(maxItems>tags.size){
                 tags.size
            }else{
                maxItems
            }
        }else{
            tags.size
        }

        repeat(items){
            TagItem(modifier = Modifier.padding(8.dp),
                tag = tags[it])
        }

    }
}

@Preview
@Composable
fun Preview2(){
    TagList(listOf("Drama","Historician","Tag muy largo","Shonen"), 2)
}