package com.example.proyectofinalopentech.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MangaPagesFavTable")
data class MangaPageLocal(
    @PrimaryKey @ColumnInfo(name = "url") val url: String,
)
