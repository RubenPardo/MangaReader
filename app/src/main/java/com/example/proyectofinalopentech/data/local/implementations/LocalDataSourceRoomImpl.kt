package com.example.proyectofinalopentech.data.local.implementations


import com.example.proyectofinalopentech.data.local.interfaces.LocalDataSource
import com.example.proyectofinalopentech.data.local.model.MangaLocal
import com.example.proyectofinalopentech.data.local.model.MangaPageLocal
import com.example.proyectofinalopentech.data.local.room.MangaFavDao
import com.example.proyectofinalopentech.data.local.room.MangaPagesFavDao

class LocalDataSourceRoomImpl (
        private val mangaFavDao: MangaFavDao,
        private val mangaPagesFavDao: MangaPagesFavDao,
    ): LocalDataSource {

    override suspend fun insertFavManga(mangaLocal: MangaLocal)
        =  mangaFavDao.insertManga(mangaLocal)

    override suspend fun getFavMangas(): List<MangaLocal> = mangaFavDao.getAll()

    override suspend fun removeFavManga(mangaLocal: MangaLocal) = mangaFavDao.removeFav(mangaLocal)

    override suspend fun isFavManga(mangaId: String): Boolean = mangaFavDao.getMangaFavById(mangaId)
    override suspend fun insertMangaPage(mangaLocal: MangaPageLocal): Long = mangaPagesFavDao.insertMangaPage(mangaLocal)

    override suspend fun getAllMangaPagesFav(): List<MangaPageLocal> = mangaPagesFavDao.getAllMangaPagesFav()

    override suspend fun removeMangaPage(mangaLocal: MangaPageLocal) = mangaPagesFavDao.removeMangaPage(mangaLocal)

    override suspend fun isFavMangaPage(url: String): Boolean = mangaPagesFavDao.isFavMangaPage(url)

}