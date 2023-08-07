package com.example.proyectofinalopentech.domain.usecases

import com.example.proyectofinalopentech.domain.model.ChapterDetail
import com.example.proyectofinalopentech.domain.model.Manga
import com.example.proyectofinalopentech.domain.model.Response
import com.example.proyectofinalopentech.domain.repositoryInterfaces.MangaRepository

class GetChapterDetailUseCase(
    private val mangaRepository: MangaRepository
) {
    suspend operator fun invoke(chapterId:String): Response<ChapterDetail>
        = mangaRepository.getChapterDetail(chapterId)

}