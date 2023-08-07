package com.example.proyectofinalopentech.data.model.mappers

import com.example.proyectofinalopentech.data.model.MangaListResponseDTO
import com.example.proyectofinalopentech.domain.model.Manga
import com.example.proyectofinalopentech.common.Utils
import com.example.proyectofinalopentech.data.model.MangaDto

fun MangaListResponseDTO.toDomain():List<Manga> {

    return data.map { d -> d.toDomain() }.toList<Manga>()
}

fun MangaDto.toDomain(): Manga{
    // tags ---------
    val tags:List<String> = this.attributes?.let { attributes ->
        attributes.tags.filter { tag1 -> tag1.attributes?.group == "genre" }.toList().map { tag -> tag.attributes?.name?.en ?: "" }
    } ?: listOf<String>()

    // cover art -------
    var coverArt:String? = null
    this.relationships.filter { relationship ->
        relationship.type == "cover_art"
    }.toList().firstOrNull()?.let {  relationshipCover -> coverArt = relationshipCover.attributes.fileName }

    // map manga
    return Manga(
        this.id ?: "",
        this.attributes?.title?.en ?: "",
        this.attributes?.description?.en ?: "",
        tags,
        Utils.parseStrigDateToDate(this.attributes?.updatedAt),
        this.attributes?.lastVolume ?: "",
        this.attributes?.lastChapter ?: "",
        this.attributes?.state ?: "",
        this.attributes?.status ?: "",
        "https://uploads.mangadex.org/covers/${this.id}/$coverArt",
        "https://uploads.mangadex.org/covers/${this.id}/$coverArt.256.jpg"
    )
}