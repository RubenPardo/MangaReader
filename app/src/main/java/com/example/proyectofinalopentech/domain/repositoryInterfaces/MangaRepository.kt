package com.example.proyectofinalopentech.domain.repositoryInterfaces

import com.example.proyectofinalopentech.domain.model.Manga
import com.example.proyectofinalopentech.domain.model.Response

interface MangaRepository {

    suspend fun getMangas(offset:Int, limit:Int): Response<List<Manga>>

}