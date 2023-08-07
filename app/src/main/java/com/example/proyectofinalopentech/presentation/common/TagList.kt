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
fun TagList(tags: List<String>) {
    FlowRow(
        maxItemsInEachRow = 5,
    ) {
        tags.forEach { tag ->
            TagItem(modifier = Modifier.padding(8.dp),
                tag = tag)
        }
    }
}

@Preview
@Composable
fun Preview2(){
    TagList(listOf("Drama","Historician","Tag muy largo","Shonen"))
}