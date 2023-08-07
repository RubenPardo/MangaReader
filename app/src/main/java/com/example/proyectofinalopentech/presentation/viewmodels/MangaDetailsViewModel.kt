package com.example.proyectofinalopentech.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinalopentech.domain.model.Chapter
import com.example.proyectofinalopentech.domain.model.Manga
import com.example.proyectofinalopentech.domain.model.Response
import com.example.proyectofinalopentech.domain.usecases.GetMangaChaptersByIdUseCase
import com.example.proyectofinalopentech.domain.usecases.GetMangaInfoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MangaDetailsViewModel(
    private val getMangaChaptersByIdUseCase: GetMangaChaptersByIdUseCase,
    private val getMangaInfoUseCase: GetMangaInfoUseCase,
): ViewModel() {

    private var currentState = MangaDetailsUiState()
    private val _uiState = MutableStateFlow<MangaDetailsUiState>(currentState)
    val uiState:StateFlow<MangaDetailsUiState> = _uiState


    fun getChapter(id:String){
        viewModelScope.launch (Dispatchers.IO){
            // loading
            emitNewState(currentState.copy(isLoadingChapters = true, isError = false))

            when(val response = getMangaChaptersByIdUseCase(id)){
                // error
                is Response.Error -> emitNewState(currentState.copy(isLoadingChapters = false, isError = true))

                // content
                is Response.Success ->
                    emitNewState(currentState.copy(isLoadingChapters = false, isError = false,
                        volumes = response.data?: emptyList()))

            }
        }

    }

    fun getMangaInfo(mangaId: String) {
        viewModelScope.launch (Dispatchers.IO){
            emitNewState(currentState.copy(isLoadingMangaInfo = true, isError = false))
            when(  val response = getMangaInfoUseCase(mangaId)){
                // error
                is Response.Error -> emitNewState(currentState.copy(
                    isLoadingMangaInfo = false, isError = true))

                // content
                is Response.Success ->
                    emitNewState(currentState.copy(
                        isLoadingMangaInfo = false, isError = false, mangaInfo = response.data))

            }
        }
    }

    private suspend fun emitNewState(newState: MangaDetailsUiState) {
        currentState = newState
       _uiState.emit(newState)
    }




}

data class MangaDetailsUiState(
    val isLoadingChapters: Boolean = false,
    val isLoadingMangaInfo: Boolean = false,
    val isError: Boolean = false,
    val messageError: String = "",
    val volumes:List<Chapter> = emptyList(),
    val mangaInfo: Manga? = null
)

