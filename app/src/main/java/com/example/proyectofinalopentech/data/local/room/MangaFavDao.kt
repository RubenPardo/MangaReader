package com.example.proyectofinalopentech.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proyectofinalopentech.data.local.model.MangaLocal

@Dao
interface MangaFavDao {

    @Query("SELECT * FROM MangaFavTable")
    suspend fun getAll(): List<MangaLocal>

    @Delete
    suspend fun removeFav(model: MangaLocal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertManga(mangaLocal: MangaLocal):Long

    @Query("SELECT EXISTS(SELECT * FROM MangaFavTable WHERE id = :mangaId)")
    fun getMangaFavById(mangaId: String): Boolean

}