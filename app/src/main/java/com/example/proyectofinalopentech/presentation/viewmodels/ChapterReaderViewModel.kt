package com.example.proyectofinalopentech.presentation.viewmodels

import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinalopentech.R
import com.example.proyectofinalopentech.domain.model.ChapterDetail
import com.example.proyectofinalopentech.domain.model.Manga
import com.example.proyectofinalopentech.domain.model.Response
import com.example.proyectofinalopentech.domain.usecases.GetChapterDetailUseCase
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

    private suspend fun emitNewState(newState: ChapterReaderUiState) {
        currentState = newState
        _uiState.emit(newState)
    }


}