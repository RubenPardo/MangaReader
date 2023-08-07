package com.example.proyectofinalopentech.domain.usecases

import com.example.proyectofinalopentech.domain.model.Manga
import com.example.proyectofinalopentech.domain.model.Response
import com.example.proyectofinalopentech.domain.repositoryInterfaces.MangaRepository

class GetMangaInfoUseCase(
    private val repository: MangaRepository
) {

    suspend operator fun invoke(mangaId: String):Response<Manga> = repository.getMangaInfo(mangaId)

}