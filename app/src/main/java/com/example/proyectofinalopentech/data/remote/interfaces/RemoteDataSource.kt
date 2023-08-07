package com.example.proyectofinalopentech.data.remote.interfaces

import com.example.proyectofinalopentech.data.model.ChapterResponseDTO
import com.example.proyectofinalopentech.data.model.MangaListResponseDTO
import com.example.proyectofinalopentech.data.model.MangaResponseDTO

interface RemoteDataSource {

    suspend fun getMangasByName(offset: Int, limit: Int, mangaName: String): MangaListResponseDTO
    suspend fun getChaptersByMangaId(mangaId:String): ChapterResponseDTO
    suspend fun getMangaById(mangaId: String): MangaResponseDTO

}