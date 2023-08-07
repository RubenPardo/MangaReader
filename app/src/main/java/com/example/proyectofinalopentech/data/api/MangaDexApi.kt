package com.example.proyectofinalopentech.data.api

import com.example.proyectofinalopentech.data.model.ChapterDetailResponseDTO
import com.example.proyectofinalopentech.data.model.ChapterResponseDTO
import com.example.proyectofinalopentech.data.model.MangaListResponseDTO
import com.example.proyectofinalopentech.data.model.MangaResponseDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MangaDexApi {


    @GET("manga?includes[]=cover_art&contentRating%5B%5D=safe")
    suspend fun getMangas(
        @Query("title") mangaName: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): MangaListResponseDTO

    @GET("manga/{id}/aggregate")
    suspend fun getChapters(
        @Path("id") id: String,
    ): ChapterResponseDTO

    @GET("manga/{id}?includes[]=cover_art")
    suspend fun getMangaById(@Path("id")mangaId: String): MangaResponseDTO

    @GET("at-home/server/")
    suspend fun getChapterDetail(@Query("id")chapterId: String): ChapterDetailResponseDTO

}