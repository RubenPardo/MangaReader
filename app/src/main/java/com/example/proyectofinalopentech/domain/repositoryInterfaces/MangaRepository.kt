package com.example.proyectofinalopentech.domain.repositoryInterfaces

import androidx.paging.PagingData
import com.example.proyectofinalopentech.domain.model.Chapter
import com.example.proyectofinalopentech.domain.model.ChapterDetail
import com.example.proyectofinalopentech.domain.model.Manga
import com.example.proyectofinalopentech.domain.model.MangaPage
import com.example.proyectofinalopentech.domain.model.Response
import kotlinx.coroutines.flow.Flow

interface MangaRepository {

    fun getMangasByName(mangaName: String): Flow<PagingData<Manga>>
    suspend fun getChaptersByMangaId(mangaId: String): Response<List<Chapter>>
    suspend fun getMangaInfo(mangaId: String): Response<Manga>
    suspend fun getFavMangas(): Response<List<Manga>>
    suspend fun saveFavManga(manga: Manga): Response<Boolean>
    suspend fun removeFavManga(manga: Manga): Response<Boolean>
    suspend fun isFavManga(mangaId: String): Response<Boolean>
    suspend fun getFavMangaPages(): Response<List<MangaPage>>
    suspend fun saveMangaPage(page: MangaPage): Response<Boolean>
    suspend fun removeMangaPage(page: MangaPage): Response<Boolean>
    suspend fun isFavMangaPage(page: MangaPage): Response<Boolean>
    suspend fun getChapterDetail(chapterId: String): Response<ChapterDetail>
}