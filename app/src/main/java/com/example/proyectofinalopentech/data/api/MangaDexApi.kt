package com.example.proyectofinalopentech.data.api

import com.example.proyectofinalopentech.data.model.MangaResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface MangaDexApi {


    @GET("manga?includes[]=cover_art")
    suspend fun getMangas(
        @Query("title") mangaName: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): MangaResponseDTO

}