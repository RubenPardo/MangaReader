package com.example.proyectofinalopentech.domain.usecases

import com.example.proyectofinalopentech.domain.model.ChapterDetail
import com.example.proyectofinalopentech.domain.model.Manga
import com.example.proyectofinalopentech.domain.model.Response
import com.example.proyectofinalopentech.domain.repositoryInterfaces.MangaRepository

class GetChapterDetailUseCase(
    private val mangaRepository: MangaRepository
) {
    suspend operator fun invoke(chapterId:String): Response<ChapterDetail> {
       return when(val response = mangaRepository.getChapterDetail(chapterId)){
            is Response.Error -> response
            is Response.Success -> {

                response.data?.let { chapterDetail ->
                    val chapterDetailWithFavs = chapterDetail.listPageUrls.map {
                        val page = it
                         when(val resFav = mangaRepository.isFavMangaPage(page)){
                            is Response.Error -> page
                            is Response.Success -> {
                                page.copy(isFav = resFav.data ?:false)
                            }
                        }
                    }.toList()

                    Response.Success(response.data.copy(listPageUrls = chapterDetailWithFavs))

                }?: Response.Error(response.message)

            }
        }
    }

}