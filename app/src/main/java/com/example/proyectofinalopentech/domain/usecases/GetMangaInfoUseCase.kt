package com.example.proyectofinalopentech.domain.usecases

import com.example.proyectofinalopentech.domain.model.Manga
import com.example.proyectofinalopentech.domain.model.Response
import com.example.proyectofinalopentech.domain.repositoryInterfaces.MangaRepository

class GetMangaInfoUseCase(
    private val repository: MangaRepository
) {

    suspend operator fun invoke(mangaId: String):Response<Manga>{
        // get manga
        return when(val res =  repository.getMangaInfo(mangaId)){
            is Response.Error -> res
            is Response.Success -> {
                // get if is fav
                when(val isFavRes = repository.isFavManga(mangaId)){
                    is Response.Error -> Response.Error(isFavRes.message)
                    is Response.Success ->{
                        val manga = res.data
                        manga?.isFav = isFavRes.data?:false
                        Response.Success(manga!!)
                    }
                }

            }
        }
    }

}