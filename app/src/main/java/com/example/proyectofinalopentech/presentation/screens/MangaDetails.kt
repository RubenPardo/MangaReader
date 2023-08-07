package com.example.proyectofinalopentech.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.proyectofinalopentech.R
import com.example.proyectofinalopentech.domain.model.Chapter
import com.example.proyectofinalopentech.domain.model.Manga
import com.example.proyectofinalopentech.domain.model.builders.MangaBuilder
import com.example.proyectofinalopentech.presentation.common.LoadingView
import com.example.proyectofinalopentech.presentation.common.SaveIcon
import com.example.proyectofinalopentech.presentation.mangalist.ErrorItemList
import com.example.proyectofinalopentech.presentation.viewmodels.MangaDetailsUiState
import com.example.proyectofinalopentech.presentation.viewmodels.MangaDetailsViewModel
import com.example.proyectofinalopentech.ui.theme.subtitleSmall
import com.example.proyectofinalopentech.ui.theme.titleMangaItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun MangaDetails(
    mangaId: String,
    mangaDetailsViewModel: MangaDetailsViewModel = koinViewModel(),
    goBack: (() -> Unit)?
) {
    LaunchedEffect(true){
        // init view model
        mangaDetailsViewModel.getChapter(mangaId)
        mangaDetailsViewModel.getMangaInfo(mangaId)
    }
    
    val uiSate = mangaDetailsViewModel.uiState.collectAsState().value

    Prueba(uiSate,mangaDetailsViewModel, mangaId)

}

@Composable
fun Prueba(uiSate: MangaDetailsUiState, mangaDetailsViewModel: MangaDetailsViewModel?, mangaId: String ="") {
    var currentTab by remember { mutableStateOf(0) }
    val tabs = listOf("Info", "Chapters")
    LazyColumn(
    ) {

        item { uiSate.mangaInfo?.let { BuildHeader(uiSate.mangaInfo) } }
        item{
            Column {
                TabRow(
                    selectedTabIndex = currentTab,
                ) {
                    tabs.forEachIndexed { index, title ->
                        Tab(text = { Text(title) },
                            selected = currentTab == index,
                            onClick = { currentTab = index }
                        )
                    }
                }
               when(currentTab){
                   0 ->  BuildInfo(uiState = uiSate, retryCallback = { mangaDetailsViewModel?.getMangaInfo(mangaId) })
                   1 ->  BuildChapters(uiState = uiSate, retryCallback = {mangaDetailsViewModel?.getChapter(mangaId)})
               }
            }
        }
    }
}

@Composable
fun BuildInfo(uiState: MangaDetailsUiState, retryCallback: () -> Unit) {
    if(uiState.isLoadingMangaInfo){
        LoadingView(modifier = Modifier
            .fillMaxSize()
            .height(400.dp))
    }else if(uiState.isError){
        ErrorItemList(
            modifier = Modifier.fillMaxSize(),
            message = stringResource(id = R.string.error_geting_chapters)) {
            retryCallback.invoke()
        }
    }else{
        uiState.mangaInfo?.let {BuildMangaInfo(uiState.mangaInfo)  }
    }
}
@Composable
fun BuildMangaInfo(mangaInfo: Manga) {
    Column(
        Modifier
            .padding(16.dp)
            .fillMaxWidth()) {

        Text(text = mangaInfo.title, style = MaterialTheme.typography.titleMangaItem)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = mangaInfo.description, style = MaterialTheme.typography.subtitleSmall)

    }
}

@Composable
fun BuildHeader(mangaInfo: Manga) {

    var fav by remember {
        mutableStateOf(false)
    }
        Box{
            AsyncImage(
                placeholder = painterResource(R.drawable.ic_launcher_background),
                model = mangaInfo.fullImageUrl,
                contentDescription = null,
                error = painterResource(R.drawable.ic_launcher_background),
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .padding(0.dp)
            )
            SaveIcon(
                modifier = Modifier
                    .padding(end = 8.dp,)
                    .align(Alignment.TopEnd)
                    .size(72.dp),
                isMarked = fav,
                iconMarked = Icons.Outlined.Bookmark,
                iconDefault= Icons.Outlined.BookmarkBorder){
                fav = !fav
            }
        }

}



@Composable
fun BuildChapters(uiState: MangaDetailsUiState, retryCallback: ()->Unit){

    if(uiState.isLoadingChapters){
        LoadingView(modifier = Modifier.fillMaxSize())
    }else if(uiState.isError){
        ErrorItemList(
            modifier = Modifier.fillMaxSize(),
            message = stringResource(id = R.string.error_geting_chapters)) {
            retryCallback.invoke()
        }
    }else{

        BuildListChapters(uiState.volumes)

    }
}

@Composable
fun BuildListChapters(chapters: List<Chapter>) {
    chapters.forEachIndexed{index,chapter ->
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
        ){
            BuildChapter(chapter)
            if(index < chapters.size-1){
                Spacer(modifier = Modifier.height(24.dp))
                Divider(color = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f))
            }else{
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }

}

@Composable
fun BuildChapter(it: Chapter) {
    Text("CHAPTER ${it.name}", modifier = Modifier.padding(start = 12.dp))
}


@Preview
@Composable
fun Preview(){
    Prueba(uiSate = MangaDetailsUiState(
        isLoadingChapters = false,
        isLoadingMangaInfo = false,
        mangaInfo = MangaBuilder().build(),
        volumes = Array(60){Chapter(it.toString(),"")}.toList()
    ),
        mangaDetailsViewModel = null )
}
