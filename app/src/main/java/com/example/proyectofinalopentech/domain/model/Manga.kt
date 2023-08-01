package com.example.proyectofinalopentech.domain.model

data class Manga(
    val id: String,
    val title: String,
    val description:String,
    val tags: List<String>,
    val state: String,
    val status: String,
    val fullImageUrl:String,
    val smallImageUrl:String,
)
