package com.example.proyectofinalopentech.data.remote.interfaces

import com.example.proyectofinalopentech.data.model.ChapterResponseDTO
import com.example.proyectofinalopentech.data.model.MangaResponseDTO

interface RemoteDataSource {

    suspend fun getMangasByName(offset: Int, limit: Int, mangaName: String): MangaResponseDTO
    suspend fun getChaptersByMangaId(mangaId:String): ChapterResponseDTO

}