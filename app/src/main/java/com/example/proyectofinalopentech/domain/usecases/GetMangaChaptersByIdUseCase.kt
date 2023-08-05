package com.example.proyectofinalopentech.domain.usecases

import com.example.proyectofinalopentech.domain.model.Response
import com.example.proyectofinalopentech.domain.model.Volume
import com.example.proyectofinalopentech.domain.repositoryInterfaces.MangaRepository

class GetMangaChaptersByIdUseCase(
    private val repository: MangaRepository
) {

    suspend operator fun invoke(mangaId: String):Response<List<Volume>> = repository.getChaptersByMangaId(mangaId)

}