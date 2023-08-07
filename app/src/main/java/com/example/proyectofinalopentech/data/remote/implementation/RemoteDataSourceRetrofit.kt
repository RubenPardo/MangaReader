package com.example.proyectofinalopentech.data.remote.implementation

import com.example.proyectofinalopentech.data.api.MangaDexApi
import com.example.proyectofinalopentech.data.model.ChapterDetailResponseDTO
import com.example.proyectofinalopentech.data.model.ChapterResponseDTO
import com.example.proyectofinalopentech.data.model.MangaListResponseDTO
import com.example.proyectofinalopentech.data.model.MangaResponseDTO
import com.example.proyectofinalopentech.data.remote.interfaces.RemoteDataSource


class RemoteDataSourceRetrofit(
    private val mangaDexApi: MangaDexApi
) : RemoteDataSource {
    override suspend fun getMangasByName(offset: Int, limit: Int, mangaName: String): MangaListResponseDTO = mangaDexApi.getMangas(mangaName,offset,limit)
    override suspend fun getChaptersByMangaId(mangaId: String): ChapterResponseDTO = mangaDexApi.getChapters(mangaId)
    override suspend fun getMangaById(mangaId: String): MangaResponseDTO = mangaDexApi.getMangaById(mangaId)
    override suspend fun getChapterDetail(chapterId: String): ChapterDetailResponseDTO = mangaDexApi.getChapterDetail(chapterId)
}