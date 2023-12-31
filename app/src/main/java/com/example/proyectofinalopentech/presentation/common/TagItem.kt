package com.example.proyectofinalopentech.presentation.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyectofinalopentech.databinding.TagLayoutBinding
import com.example.proyectofinalopentech.ui.theme.tagStyle
import androidx.compose.ui.viewinterop.AndroidViewBinding

@Composable
fun TagItem(tag: String, modifier: Modifier){


    AndroidViewBinding(TagLayoutBinding::inflate) {
        tvTagText.text = tag
    }
    /*Card (
        modifier = modifier,
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(Color.LightGray)){
        Text(
            modifier = Modifier
                .clearAndSetSemantics { contentDescription = "Manga Tag $tag" }
                .padding(vertical = 4.dp, horizontal = 8.dp),
            text = tag,
            style = MaterialTheme.typography.tagStyle,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }*/
}

@Preview
@Composable
fun Preview(){
    TagItem(tag = "Drama", modifier = Modifier.padding(8.dp))
}