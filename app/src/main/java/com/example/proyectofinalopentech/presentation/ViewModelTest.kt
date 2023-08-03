package com.example.proyectofinalopentech.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.proyectofinalopentech.domain.model.Manga
import com.example.proyectofinalopentech.domain.usecases.GetMangasUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch

class ViewModelTest(
    getMangasUseCase: GetMangasUseCase
): ViewModel() {




    var pagingData = getMangasUseCase.invoke(this@ViewModelTest.viewModelScope)



}