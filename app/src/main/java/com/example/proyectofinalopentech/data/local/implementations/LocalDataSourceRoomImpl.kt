package com.example.proyectofinalopentech.data.local.implementations


import com.example.proyectofinalopentech.data.local.interfaces.LocalDataSource
import com.example.proyectofinalopentech.data.local.model.MangaLocal
import com.example.proyectofinalopentech.data.local.room.MangaFavDao

class LocalDataSourceRoomImpl (
        private val mangaFavDao: MangaFavDao
    ): LocalDataSource {

    override suspend fun insertFavManga(mangaLocal: MangaLocal)
        =  mangaFavDao.insertManga(mangaLocal)

    override suspend fun getFavMangas(): List<MangaLocal> = mangaFavDao.getAll()

    override suspend fun removeFavManga(mangaLocal: MangaLocal) = mangaFavDao.removeFav(mangaLocal)

    override suspend fun isFavManga(mangaId: String): Boolean = mangaFavDao.getMangaFavById(mangaId)

}