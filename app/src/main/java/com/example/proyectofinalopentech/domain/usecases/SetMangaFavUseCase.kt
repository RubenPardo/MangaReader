package com.example.proyectofinalopentech.domain.usecases

import com.example.proyectofinalopentech.domain.model.Manga
import com.example.proyectofinalopentech.domain.model.Response
import com.example.proyectofinalopentech.domain.repositoryInterfaces.MangaRepository

class SetMangaFavUseCase(
    private val repository: MangaRepository
) {

    suspend operator fun invoke(manga:Manga): Response<Boolean> {
        return if(manga.isFav){
            repository.removeFavManga(manga)
        }else{
            repository.saveFavManga(manga)
        }
    }

}