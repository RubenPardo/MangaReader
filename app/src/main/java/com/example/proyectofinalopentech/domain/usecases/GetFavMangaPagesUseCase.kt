package com.example.proyectofinalopentech.domain.usecases

import com.example.proyectofinalopentech.domain.model.Manga
import com.example.proyectofinalopentech.domain.model.MangaPage
import com.example.proyectofinalopentech.domain.model.Response
import com.example.proyectofinalopentech.domain.repositoryInterfaces.MangaRepository

class GetFavMangaPagesUseCase(
    private val mangaRepository: MangaRepository
) {
    suspend operator fun invoke():Response<List<MangaPage>>{
        return when(val res = mangaRepository.getFavMangaPages()){
            is Response.Error ->  res
            is Response.Success -> Response.Success(res.data!!.reversed())
        }
    }
}