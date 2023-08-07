package com.example.proyectofinalopentech.data.local.interfaces

import com.example.proyectofinalopentech.data.local.model.MangaLocal


interface LocalDataSource {
    suspend fun insertFavManga(mangaLocal: MangaLocal):Long
    suspend fun getFavMangas() : List<MangaLocal>
    suspend fun removeFavManga(mangaLocal:MangaLocal)
    suspend fun isFavManga(mangaId:String):Boolean
}