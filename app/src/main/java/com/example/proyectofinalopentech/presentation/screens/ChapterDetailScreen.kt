package com.example.proyectofinalopentech.presentation.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.HorizontalDistribute
import androidx.compose.material.icons.filled.VerticalDistribute
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.proyectofinalopentech.R
import com.example.proyectofinalopentech.domain.model.MangaPage
import com.example.proyectofinalopentech.presentation.common.LoadingView
import com.example.proyectofinalopentech.presentation.common.SaveIcon
import com.example.proyectofinalopentech.presentation.mangalist.ErrorItemList
import com.example.proyectofinalopentech.presentation.viewmodels.ChapterReaderViewModel
import com.example.proyectofinalopentech.ui.theme.subtitleSmall
import net.engawapg.lib.zoomable.rememberZoomState
import net.engawapg.lib.zoomable.zoomable
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChapterDetailScreen(
    chapterId: String,
    chapterReaderViewModel: ChapterReaderViewModel = koinViewModel(),
    goBack: () -> Unit
) {
    LaunchedEffect(true ){
        chapterReaderViewModel.getChapterDetail(chapterId)
    }

    val uiState = chapterReaderViewModel.uiState.collectAsState().value

    if(uiState.isLoading){
        LoadingView(modifier = Modifier.fillMaxSize())
    }else if(uiState.isError){

        Column{
            IconButton(onClick = {goBack.invoke()}) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = stringResource(
                    id = R.string.go_back_to_manga_details
                ))
            }
            ErrorItemList(message = stringResource(id = R.string.unable_to_load_chapter),modifier = Modifier.fillMaxSize()) { chapterReaderViewModel.getChapterDetail(chapterId) }
        }
    } else{
        uiState.chapterDetail?.let {chapterDetail->

            val pagerState = rememberPagerState(
                initialPage = 0,
                initialPageOffsetFraction = 0.0f
            ) {
                chapterDetail.listPageUrls.size
            }

            var readerMode by remember { mutableStateOf(false) } // false = vertical
            var isFav by remember { mutableStateOf(false) } // false = vertical


            Column(verticalArrangement = Arrangement.SpaceBetween) {
                ChapterInfo(
                    maxPages = pagerState.pageCount,
                    currentPage = pagerState.currentPage+1,
                    readerMode = readerMode,
                    isFav = isFav,
                    changeReaderModeCallback = {readerMode = !readerMode},
                    setFavCallback = {isFav = !isFav},
                    goBack = goBack
                )

                if(readerMode)
                    HorizontalReader(pages = chapterDetail.listPageUrls,pagerState = pagerState)
                else
                    VerticalReader(pages = chapterDetail.listPageUrls,pagerState = pagerState)




            }
        }
    }



}

@Composable
fun ChapterInfo(
    maxPages: Int, currentPage: Int,
    readerMode: Boolean,
    isFav: Boolean,
    changeReaderModeCallback: () -> Unit,
    setFavCallback: () -> Unit,
    goBack: () -> Unit,
) {

    Row(
        Modifier
            .fillMaxWidth()
            .shadow(elevation = 1.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {

        IconButton(onClick = {goBack.invoke()}) {
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = stringResource(
                id = R.string.go_back_to_manga_details
            ))
        }

        ChangeReaderModeIcon(readerMode){changeReaderModeCallback.invoke()}

        Text(text = "${currentPage}/${maxPages}", style = MaterialTheme.typography.subtitleSmall )

        SaveIcon(
            size = 32.dp,
            modifier = Modifier
                .padding(end = 8.dp,)
                .size(72.dp),
            isMarked = isFav,
            withShadow = false,
            iconMarked = Icons.Outlined.Bookmark,
            iconDefault= Icons.Outlined.BookmarkBorder){
            setFavCallback.invoke()

        }
    }
}

@Composable
fun ChangeReaderModeIcon(readerMode: Boolean, callback: () -> Unit) {
    IconButton(onClick = { callback.invoke()}) {
        Icon(
            imageVector = if(!readerMode) Icons.Filled.VerticalDistribute else Icons.Filled.HorizontalDistribute,
            contentDescription = if(!readerMode) stringResource(id = R.string.reader_mode_vertical) else stringResource(id = R.string.reader_mode_horizontal),
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalReader(pages: List<MangaPage>, pagerState: PagerState){
    HorizontalPager(
        modifier = Modifier,
        state = pagerState,
        pageSpacing = 0.dp,
        userScrollEnabled = true,
        reverseLayout = false,
        contentPadding = PaddingValues(0.dp),
        beyondBoundsPageCount = 0,
        pageSize = PageSize.Fill,
        flingBehavior = PagerDefaults.flingBehavior(state = pagerState),
        key = null,
        pageNestedScrollConnection = PagerDefaults.pageNestedScrollConnection(
            Orientation.Horizontal
        ),
        pageContent = { pageIndex ->
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    modifier = Modifier
                        .zoomable(rememberZoomState())
                        .fillMaxSize(),
                    placeholder = painterResource(R.drawable.ic_launcher_background),
                    model = pages[pageIndex].pageLR,
                    contentDescription = stringResource(id = R.string.page) + pageIndex,
                    error = painterResource(R.drawable.ic_launcher_background),
                    contentScale = ContentScale.FillWidth

                )
            }
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun VerticalReader(pages: List<MangaPage>, pagerState: PagerState){
    VerticalPager(
        modifier = Modifier,
        state = pagerState,
        pageSpacing = 0.dp,
        userScrollEnabled = true,
        reverseLayout = false,
        contentPadding = PaddingValues(0.dp),
        beyondBoundsPageCount = 0,
        pageSize = PageSize.Fill,
        flingBehavior = PagerDefaults.flingBehavior(state = pagerState),
        key = null,
        pageNestedScrollConnection = PagerDefaults.pageNestedScrollConnection(
            Orientation.Vertical
        ),
        pageContent = { pageIndex ->
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    modifier = Modifier
                        .zoomable(rememberZoomState())
                        .fillMaxSize(),
                    placeholder = painterResource(R.drawable.ic_launcher_background),
                    model = pages[pageIndex].pageLR,
                    contentDescription = stringResource(id = R.string.page) + pageIndex,
                    error = painterResource(R.drawable.ic_launcher_background),
                    contentScale = ContentScale.FillWidth

                )
            }
        }
    )
}