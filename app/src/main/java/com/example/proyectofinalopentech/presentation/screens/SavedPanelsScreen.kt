package com.example.proyectofinalopentech.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.proyectofinalopentech.R
import com.example.proyectofinalopentech.presentation.common.EmptyView
import com.example.proyectofinalopentech.presentation.common.LoadingView
import com.example.proyectofinalopentech.presentation.mangalist.ErrorItemList
import com.example.proyectofinalopentech.presentation.viewmodels.MyPagesViewModel
import net.engawapg.lib.zoomable.rememberZoomState
import net.engawapg.lib.zoomable.zoomable
import org.koin.androidx.compose.koinViewModel

@Composable
fun SavedPanelsScreen(
    scrollState: LazyListState,
    myMangasViewModel: MyPagesViewModel = koinViewModel(),
    goToChapterDetail: (chapterId:String, page:Int)->Unit,

    ) {

    LaunchedEffect(true ){
        myMangasViewModel.getFavPanels()
    }

    val uiState = myMangasViewModel.uiState.collectAsState().value

    if(uiState.isLoading){
        LoadingView(modifier = Modifier.fillMaxSize())
    }else if(uiState.isError){
        ErrorItemList(message = uiState.messageError) { myMangasViewModel.getFavPanels() }
    } else{

        if(uiState.pages.isEmpty()){
            EmptyView(stringResource(id = R.string.empty_saved_panels))
        }else{
            LazyVerticalGrid(
                modifier = Modifier.padding(8.dp),
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(8.dp)
            ){

                items(uiState.pages){
                    AsyncImage(
                        modifier = Modifier
                            .zoomable(rememberZoomState())
                            .fillMaxSize()
                            .clickable { goToChapterDetail.invoke(it.chapterId,it.page) }
                            .padding(8.dp),
                        placeholder = painterResource(R.drawable.ic_launcher_background),
                        model = it.pageLR,
                        contentDescription = stringResource(id = R.string.fav_panel),
                        error = painterResource(R.drawable.ic_launcher_background),
                        contentScale = ContentScale.FillWidth

                    )
                }

            }
        }


    }

}


