package com.example.proyectofinalopentech.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinalopentech.domain.model.Manga
import com.example.proyectofinalopentech.domain.model.Response
import com.example.proyectofinalopentech.domain.usecases.GetFavMangasUseCase
import com.example.proyectofinalopentech.domain.usecases.SetMangaFavUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


data class MyMangasUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val messageError: String = "",
    val favMangas:List<Manga> = emptyList(),
)


class MyMangasViewModel(
    private val getMangaInfoUseCase: GetFavMangasUseCase,
):ViewModel() {

    private var currentState = MyMangasUiState()
    private val _uiState = MutableStateFlow(currentState)
    val uiState: StateFlow<MyMangasUiState> = _uiState

    fun getFavMangas() = viewModelScope.launch(Dispatchers.IO) {
        emitNewState(currentState.copy(isLoading = true, isError = false))

        when(val res =getMangaInfoUseCase()){
            is Response.Error -> emitNewState(currentState.copy(isError = true, messageError = res.message?:""))
            is Response.Success -> {
                emitNewState(currentState.copy(isLoading = false, isError = false, favMangas = res.data?: emptyList()))
            }
        }
    }

    private suspend fun emitNewState(newState: MyMangasUiState) {
        currentState = newState
        _uiState.emit(newState)
    }


}