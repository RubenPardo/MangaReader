package com.example.proyectofinalopentech.presentation.viewmodels

import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinalopentech.R
import com.example.proyectofinalopentech.domain.model.ChapterDetail
import com.example.proyectofinalopentech.domain.model.Manga
import com.example.proyectofinalopentech.domain.model.MangaPage
import com.example.proyectofinalopentech.domain.model.Response
import com.example.proyectofinalopentech.domain.usecases.GetChapterDetailUseCase
import com.example.proyectofinalopentech.domain.usecases.SetMangaPageFavUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class ChapterReaderUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val messageError: String = "",
    val chapterDetail:ChapterDetail? = null,
)


class ChapterReaderViewModel(
    private val getChapterDetailUseCase: GetChapterDetailUseCase,
    private val setMangaPageFavUseCase: SetMangaPageFavUseCase,
): ViewModel() {

    private var currentState = ChapterReaderUiState()
    private val _uiState = MutableStateFlow(currentState)
    val uiState: StateFlow<ChapterReaderUiState> = _uiState

    fun getChapterDetail(chapterId:String) = viewModelScope.launch(Dispatchers.IO) {
        emitNewState(currentState.copy(isLoading = true, isError = false))

        when(val res =getChapterDetailUseCase(chapterId)){
            is Response.Error -> emitNewState(currentState.copy(isLoading = false, isError = true, messageError = res.message?:""))
            is Response.Success -> {
                emitNewState(currentState.copy(isLoading = false, isError = false, chapterDetail = res.data))
            }
        }
    }

    fun setFav(mangaPageIndex: Int) {
        viewModelScope.launch (Dispatchers.IO){
            currentState.chapterDetail?.let {chapterDetail ->
                val currentPage = chapterDetail.listPageUrls[mangaPageIndex]

                when(  val response = setMangaPageFavUseCase(currentPage)){
                    // error
                    is Response.Error -> {
                        println("CHAPTER DETAIL ERROR: ${response.message}")
                    }
                    // content
                    is Response.Success ->{
                        val newPage = chapterDetail.listPageUrls[mangaPageIndex].copy(isFav = !currentPage.isFav)
                        val newList = chapterDetail.listPageUrls.toMutableList()
                        newList[mangaPageIndex] = newPage
                        emitNewState(currentState.copy(chapterDetail = chapterDetail.copy(listPageUrls = newList)))
                    }

                }
            }


        }
    }

    private suspend fun emitNewState(newState: ChapterReaderUiState) {
        currentState = newState
        _uiState.emit(newState)
    }


}