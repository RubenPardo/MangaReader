package com.example.proyectofinalopentech.domain.usecases

import com.example.proyectofinalopentech.domain.model.Manga
import com.example.proyectofinalopentech.domain.model.Response
import com.example.proyectofinalopentech.domain.repositoryInterfaces.MangaRepository

class GetMangasUseCase(
    private val repository: MangaRepository
) {

    suspend fun invoke(offset:Int, limit:Int): Response<List<Manga>> = repository.getMangas(offset, limit)

}