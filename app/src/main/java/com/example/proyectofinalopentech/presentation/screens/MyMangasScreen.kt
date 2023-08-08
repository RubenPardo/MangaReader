package com.example.proyectofinalopentech.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.proyectofinalopentech.navigation.Screen
import com.example.proyectofinalopentech.presentation.common.LoadingView
import com.example.proyectofinalopentech.presentation.mangalist.ErrorItemList
import com.example.proyectofinalopentech.presentation.mangalist.MangaItemList
import com.example.proyectofinalopentech.presentation.viewmodels.MyMangasViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MyMangasScreen(
    scrollState: LazyListState,
    myMangasViewModel: MyMangasViewModel = koinViewModel(),
    gotToMangaDetails: (mangaId:String) -> Unit,
) {
    LaunchedEffect(true ){
        myMangasViewModel.getFavMangas()
    }

    val uiState = myMangasViewModel.uiState.collectAsState().value

    if(uiState.isLoading){
        LoadingView()
    }else if(uiState.isError){
        ErrorItemList(message = uiState.messageError) { myMangasViewModel.getFavMangas() }
    } else{
        LazyColumn(
            state = scrollState
        ){

            items(uiState.favMangas.size){index->
                MangaItemList(manga = uiState.favMangas[index],gotToMangaDetails,scrollState)
                if(index == uiState.favMangas.size-1){
                    Spacer(modifier = Modifier.height(56.dp))
                }
            }
        }
    }

}
