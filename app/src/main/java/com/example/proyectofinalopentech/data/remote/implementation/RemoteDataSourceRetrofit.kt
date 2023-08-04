package com.example.proyectofinalopentech.data.remote.implementation

import com.example.proyectofinalopentech.data.api.MangaDexApi
import com.example.proyectofinalopentech.data.model.ChapterResponseDTO
import com.example.proyectofinalopentech.data.model.MangaResponseDTO
import com.example.proyectofinalopentech.data.remote.interfaces.RemoteDataSource



class RemoteDataSourceRetrofit(
    private val mangaDexApi: MangaDexApi
) : RemoteDataSource {
    override suspend fun getMangasByName(offset: Int, limit: Int, mangaName: String): MangaResponseDTO = mangaDexApi.getMangas(mangaName,offset,limit)
    override suspend fun getChaptersByMangaId(mangaId: String): ChapterResponseDTO = mangaDexApi.getChapters(mangaId)
}