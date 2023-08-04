package com.example.proyectofinalopentech.presentation.mangalist

import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.proyectofinalopentech.R
import com.example.proyectofinalopentech.common.format
import com.example.proyectofinalopentech.domain.model.Manga
import com.example.proyectofinalopentech.domain.model.builders.MangaBuilder
import com.example.proyectofinalopentech.ui.theme.primaryButton
import com.example.proyectofinalopentech.ui.theme.subtitleLarge
import com.example.proyectofinalopentech.ui.theme.subtitleSmall
import com.example.proyectofinalopentech.ui.theme.titleMangaItem
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun MangaItemList(
    manga: Manga,
    gotToMangaDetails: (mangaId: String) -> Unit,
    scrollState: LazyListState
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
    ) {
        BuildImage(manga.smallImageUrl)
        Spacer(modifier = Modifier.width(16.dp))
        BuildMangaContent(manga,gotToMangaDetails,scrollState)
    }

}

@Composable
fun BuildMangaContent(
    manga: Manga,
    gotToMangaDetails: (mangaId: String) -> Unit,
    scrollState: LazyListState
) {
    Column (
        Modifier.height(190.dp),
        verticalArrangement = Arrangement.SpaceBetween,
    ){
        Column {
            Spacer(modifier = Modifier.height(8.dp))
            Text(manga.title, style = MaterialTheme.typography.titleMangaItem, maxLines = 2, overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {

                if(manga.lastUpdate!=null)Text(manga.lastUpdate.format(), style = MaterialTheme.typography.subtitleSmall, maxLines = 1, overflow = TextOverflow.Ellipsis)

                Spacer(modifier = Modifier.width(8.dp))

                if(manga.lastVolume.isNotEmpty()){
                    Text("Vol. ${manga.lastVolume}", style = MaterialTheme.typography.subtitleLarge, maxLines = 1, overflow = TextOverflow.Ellipsis)
                }else if(manga.lastChapter.isNotEmpty()){
                    Text("Chapter. ${manga.lastChapter}", style = MaterialTheme.typography.subtitleLarge, maxLines = 1, overflow = TextOverflow.Ellipsis)
                }

            }
        }
        BuildButtonReadNow(manga.id,gotToMangaDetails,scrollState)

    }
}

@Composable
fun BuildButtonReadNow(
    mangaId: String,
    gotToMangaDetails: (mangaId: String) -> Unit,
    scrollState: LazyListState
) {
    val scope = rememberCoroutineScope()

    Button(
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
        onClick = {
            // scroll up to show again the top bar if it was hidden
            scope.launch {
                scrollState.scroll(scrollPriority = MutatePriority.UserInput){
                    this.scrollBy(-0.5f)
                }
            }
            gotToMangaDetails.invoke(mangaId)

        })
    {
        Text("Read Now",style = MaterialTheme.typography.primaryButton)
    }
}


@Composable
fun BuildImage(smallImageUrl: String) {
    Card(shape = RoundedCornerShape(18.dp)) {
        AsyncImage(
            placeholder = painterResource(R.drawable.ic_launcher_background),
            model = smallImageUrl,
            contentDescription = null,
            error = painterResource(R.drawable.ic_launcher_background),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(130.dp)
                .height(200.dp)
                .padding(0.dp)

        )
    }
}

