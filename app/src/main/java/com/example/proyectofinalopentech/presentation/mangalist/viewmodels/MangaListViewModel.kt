package com.example.proyectofinalopentech.presentation.mangalist.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.proyectofinalopentech.domain.model.Manga
import com.example.proyectofinalopentech.domain.repositoryInterfaces.MangaRepository
import com.example.proyectofinalopentech.domain.usecases.GetMangasByNameUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch

class MangaListViewModel(
    private val getMangasByNameUseCase: GetMangasByNameUseCase,
    private val repository: MangaRepository,
): ViewModel() {


    private var mangaName:String? = null
    private var flowDataMangas: Flow<PagingData<Manga>> = emptyFlow()


    fun get(mangaName:String):Flow<PagingData<Manga>>  {

        viewModelScope.launch (Dispatchers.IO){
            val response = repository.getChaptersByMangaId("67c03443-8b06-44e1-9784-55b33bc3428d")
            println(response)
        }



        if(this.mangaName==null){
            this.mangaName = mangaName
            flowDataMangas = getMangasByNameUseCase.invoke(mangaName).cachedIn(this@MangaListViewModel.viewModelScope)
        }else if(mangaName!=this.mangaName){
            flowDataMangas = getMangasByNameUseCase.invoke(mangaName).cachedIn(this@MangaListViewModel.viewModelScope)
            this.mangaName = mangaName
        }

        return flowDataMangas
    }



}