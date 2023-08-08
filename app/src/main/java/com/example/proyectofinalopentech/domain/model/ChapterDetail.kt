package com.example.proyectofinalopentech.domain.model

data class ChapterDetail(
    val listPageUrls: List<MangaPage>
)

data class MangaPage(
    val pageHR:String,
    val pageLR:String,
    val isFav:Boolean = false,
)