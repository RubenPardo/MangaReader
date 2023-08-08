package com.example.proyectofinalopentech.domain.usecases

import com.example.proyectofinalopentech.domain.model.Manga
import com.example.proyectofinalopentech.domain.model.MangaPage
import com.example.proyectofinalopentech.domain.model.Response
import com.example.proyectofinalopentech.domain.repositoryInterfaces.MangaRepository

class SetMangaPageFavUseCase(
    private val repository: MangaRepository
) {

    suspend operator fun invoke(manga:MangaPage): Response<Boolean> {
        return if(manga.isFav){
            repository.removeMangaPage(manga)
        }else{
            repository.saveMangaPage(manga)
        }
    }

}