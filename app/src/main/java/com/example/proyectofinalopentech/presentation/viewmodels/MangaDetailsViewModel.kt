package com.example.proyectofinalopentech.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinalopentech.domain.model.Chapter
import com.example.proyectofinalopentech.domain.model.Response
import com.example.proyectofinalopentech.domain.usecases.GetMangaChaptersByIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MangaDetailsViewModel(
    private val getMangaChaptersByIdUseCase: GetMangaChaptersByIdUseCase,
): ViewModel() {

    private val currentState = MangaDetailsUiState()
    private val _uiState = MutableStateFlow<MangaDetailsUiState>(currentState)
    val uiState:StateFlow<MangaDetailsUiState> = _uiState


    fun getChapter(id:String){
        viewModelScope.launch (Dispatchers.IO){
            // loading
            emitNewState(currentState.copy(isLoading = true, isError = false))

            when(val response = getMangaChaptersByIdUseCase(id)){
                // error
                is Response.Error -> emitNewState(currentState.copy(isLoading = false, isError = true))

                // content
                is Response.Success ->
                    emitNewState(currentState.copy(isLoading = false, isError = false,
                        volumes = response.data?: emptyList()))

            }
        }

    }

    private suspend fun emitNewState(newState: MangaDetailsUiState) {
       _uiState.emit(newState)
    }


}

data class MangaDetailsUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val messageError: String = "",
    val volumes:List<Chapter> = emptyList()
)

