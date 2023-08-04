package com.example.proyectofinalopentech.domain.repositoryInterfaces

import androidx.paging.PagingData
import com.example.proyectofinalopentech.data.model.ChapterResponseDTO
import com.example.proyectofinalopentech.data.model.MangaResponseDTO
import com.example.proyectofinalopentech.domain.model.Manga
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface MangaRepository {

    fun getMangasByName(mangaName: String): Flow<PagingData<Manga>>
    suspend fun getChaptersByMangaId(mangaId: String): ChapterResponseDTO

}