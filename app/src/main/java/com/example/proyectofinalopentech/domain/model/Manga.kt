package com.example.proyectofinalopentech.domain.model

import java.util.Date

data class Manga(
    val id: String,
    val title: String,
    val description:String,
    val tags: List<String>,
    val lastUpdate: Date?,
    val lastVolume: String,
    val lastChapter: String,
    val state: String,
    val status: String,
    val fullImageUrl:String,
    val smallImageUrl:String,
    var isFav:Boolean = false
)
