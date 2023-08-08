package com.example.proyectofinalopentech.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proyectofinalopentech.data.local.model.MangaLocal
import com.example.proyectofinalopentech.data.local.model.MangaPageLocal

@Dao
interface MangaPagesFavDao {

    @Query("SELECT * FROM MangaPagesFavTable")
    suspend fun getAllMangaPagesFav(): List<MangaPageLocal>

    @Delete
    suspend fun removeMangaPage(model: MangaPageLocal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMangaPage(mangaLocal: MangaPageLocal):Long

    @Query("SELECT EXISTS(SELECT * FROM MangaPagesFavTable WHERE url = :url)")
    fun isFavMangaPage(url: String): Boolean

}