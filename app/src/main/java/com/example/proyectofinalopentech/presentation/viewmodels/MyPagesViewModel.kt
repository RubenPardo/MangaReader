package com.example.proyectofinalopentech.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinalopentech.domain.model.MangaPage
import com.example.proyectofinalopentech.domain.model.Response
import com.example.proyectofinalopentech.domain.usecases.GetFavMangaPagesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch



data class MyPagesUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val messageError: String = "",
    val pages: List<MangaPage> = emptyList(),
)

class MyPagesViewModel (
    private val getFavMangaPagesUseCase: GetFavMangaPagesUseCase,
): ViewModel() {

    private var currentState = MyPagesUiState()
    private val _uiState = MutableStateFlow(currentState)
    val uiState: StateFlow<MyPagesUiState> = _uiState

    fun getFavPanels() = viewModelScope.launch(Dispatchers.IO) {
        emitNewState(currentState.copy(isLoading = true, isError = false))

        when(val res =getFavMangaPagesUseCase()){
            is Response.Error -> emitNewState(currentState.copy(isLoading = false, isError = true, messageError = res.message?:""))
            is Response.Success -> {
                emitNewState(currentState.copy(isLoading = false, isError = false, pages = res.data?: emptyList()))
            }
        }
    }

    private suspend fun emitNewState(newState: MyPagesUiState) {
        currentState = newState
        _uiState.emit(newState)
    }


}