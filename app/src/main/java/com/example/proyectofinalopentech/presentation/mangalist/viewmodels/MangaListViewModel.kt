package com.example.proyectofinalopentech.presentation.mangalist.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.proyectofinalopentech.domain.usecases.GetMangasByNameUseCase

class MangaListViewModel(
    private val getMangasByNameUseCase: GetMangasByNameUseCase
): ViewModel() {



    fun get(mangaName:String)
        = getMangasByNameUseCase.invoke(mangaName).cachedIn(this@MangaListViewModel.viewModelScope)



}