package com.example.proyectofinalopentech.data.remote.interfaces

import com.example.proyectofinalopentech.data.model.MangaResponseDTO

interface RemoteDataSource {

    suspend fun getMangaList(offset:Int, limit:Int): MangaResponseDTO

}