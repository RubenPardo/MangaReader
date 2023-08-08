package com.example.proyectofinalopentech.data.local.interfaces

import com.example.proyectofinalopentech.data.local.model.MangaLocal
import com.example.proyectofinalopentech.data.local.model.MangaPageLocal


interface LocalDataSource {
    suspend fun insertFavManga(mangaLocal: MangaLocal):Long
    suspend fun getFavMangas() : List<MangaLocal>
    suspend fun removeFavManga(mangaLocal:MangaLocal)
    suspend fun isFavManga(mangaId:String):Boolean

    suspend fun insertMangaPage(mangaLocal: MangaPageLocal):Long
    suspend fun getAllMangaPagesFav() : List<MangaPageLocal>
    suspend fun removeMangaPage(mangaLocal:MangaPageLocal)
    suspend fun isFavMangaPage(url:String):Boolean
}