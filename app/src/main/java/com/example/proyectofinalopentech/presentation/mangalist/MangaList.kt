package com.example.proyectofinalopentech.presentation.mangalist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.proyectofinalopentech.presentation.common.LoadingView
import com.example.proyectofinalopentech.presentation.mangalist.viewmodels.MangaListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MangaList(
    mangaName:String,
    mangaListViewModel: MangaListViewModel = koinViewModel()
) {

    val pagingData = mangaListViewModel.get(mangaName = mangaName).collectAsLazyPagingItems()

    LazyColumn(modifier = Modifier.fillMaxSize()){

        items(pagingData.itemCount){ index ->
            pagingData[index]?.let {
                MangaItemList(manga = it)
            }

        }
        pagingData.apply {
            when {
                // cuando esta cargando por primera vez
                loadState.refresh is LoadState.Loading -> {
                    item{ LoadingView(modifier = Modifier.fillMaxSize()) }
                }

                // cargando mas elementos
                loadState.append is LoadState.Loading -> { item{ LoadingItemList() } }

                // cuando la priemra carga da error
                loadState.refresh is LoadState.Error -> {
                    val e = pagingData.loadState.refresh as LoadState.Error
                    item{
                        ErrorItemList(
                            message = e.error.localizedMessage!!,
                            modifier = Modifier.fillParentMaxSize(),
                            onClickRetry = { retry() }
                        )
                    }
                }
                // cuando cargar mas elementos da error
                loadState.append is LoadState.Error -> {
                    val e = pagingData.loadState.append as LoadState.Error
                    item {
                        ErrorItemList(
                            message = e.error.localizedMessage!!,
                            onClickRetry = { retry() }
                        )
                    }
                }
            }
        }

    }

}