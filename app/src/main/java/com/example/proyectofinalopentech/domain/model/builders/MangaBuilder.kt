package com.example.proyectofinalopentech.domain.model.builders

import com.example.proyectofinalopentech.common.Utils
import com.example.proyectofinalopentech.domain.BASE_URL_IMAGES
import com.example.proyectofinalopentech.domain.model.Manga
import java.util.Date

class MangaBuilder {
    val id: String = "30196491-8fc2-4961-8886-a58f898b1b3e"
    var title: String = "Titulo"
    var lastUpdate: Date = Date()
    var lastVolume: String = "25"
    var lastChapter: String = "321"
    val description:String = "Descripcion"
    val tags: List<String> = listOf("Tag 1","Tag 2")
    val state: String = "published"
    val status: String = "ongoing"
    val fullImageUrl:String = "${BASE_URL_IMAGES}${id}/f450b175-9a3c-472d-a16d-e324be240b8a.jpg.256.jpg"
    val smallImageUrl:String = "${BASE_URL_IMAGES}${id}/f450b175-9a3c-472d-a16d-e324be240b8a.jpg"


    fun build() : Manga = Manga(
        id, title, description,tags, lastUpdate ,lastVolume, lastChapter,
        state,status,fullImageUrl,smallImageUrl
    )

    fun withTtitle(title: String): MangaBuilder {
        this.title = title
        return this
    }

    fun withDate(date: String): MangaBuilder {
        this.lastUpdate = Utils.parseStrigDateToDate(date) ?: Date()
        return this
    }
}