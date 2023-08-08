package com.example.proyectofinalopentech.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.proyectofinalopentech.data.local.model.MangaLocal
import com.example.proyectofinalopentech.data.local.model.MangaPageLocal

@Database(entities = [MangaLocal::class,MangaPageLocal::class], version = 1)
//Database(entities = [SuperHeroLocal::class, AnotherLocal::class], version = 1)
abstract class MangaDatabase : RoomDatabase() {
    // List of Dao
    abstract fun mangaFavDao(): MangaFavDao
    abstract fun mangaPageFavDao(): MangaPagesFavDao
}