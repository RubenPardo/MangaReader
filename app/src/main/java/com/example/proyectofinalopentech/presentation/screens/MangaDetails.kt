package com.example.proyectofinalopentech.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyectofinalopentech.R
import com.example.proyectofinalopentech.domain.model.Chapter
import com.example.proyectofinalopentech.presentation.common.LoadingView
import com.example.proyectofinalopentech.presentation.mangalist.ErrorItemList
import com.example.proyectofinalopentech.presentation.viewmodels.MangaDetailsViewModel
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
    }
    
    val uiSate = mangaDetailsViewModel.uiState.collectAsState().value
    
    if(uiSate.isLoading){
        LoadingView(modifier = Modifier.fillMaxSize())
    }else if(uiSate.isError){
            ErrorItemList(message = stringResource(id = R.string.error_geting_chapters)) {
                mangaDetailsViewModel.getChapter(mangaId)
            }
    }else{

        BuildVolumes(uiSate.volumes)

    }


}

@Composable
fun BuildVolumes(chapters: List<Chapter>) {
    LazyColumn (modifier = Modifier.fillMaxWidth()){
        items(chapters.size){index ->
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top=12.dp),
            ){
                BuildChapter(chapters[index])
                if(index < chapters.size-1){
                    Spacer(modifier = Modifier.height(12.dp))
                    Divider(color = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f))
                }
            }
        }
    }
}

@Composable
fun BuildChapter(it: Chapter) {
    Text("CHAPTER ${it.name}")
}

@Composable
fun BuildVolumeName(name: String) {
    if(name != "none"){
        Text("${stringResource(id = R.string.volume)} $name")
    }
}

@Preview
@Composable
fun Preview(){
    BuildVolumes(
            Array(100){
                Chapter(it.toString(),"")
            }.toList()
        )
}
