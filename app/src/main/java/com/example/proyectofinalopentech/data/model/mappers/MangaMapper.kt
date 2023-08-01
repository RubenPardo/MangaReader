package com.example.proyectofinalopentech.data.model.mappers

import com.example.proyectofinalopentech.data.model.MangaResponseDTO
import com.example.proyectofinalopentech.domain.model.Manga

fun MangaResponseDTO.toDomain():List<Manga> {
    return data.map { d ->

        // tags ---------
        val tags:List<String> = d.attributes?.let { attributes ->
            attributes.tags.filter { tag1 -> tag1.attributes?.group == "genre" }.toList().map { tag -> tag.attributes?.name?.en ?: "" }
        } ?: listOf<String>()

        // cover art -------
        var coverArt:String? = null
        d.relationships.filter { relationship ->
            relationship.type == "cover_art"
        }.toList().firstOrNull()?.let {  relationshipCover -> coverArt = relationshipCover.attributes.fileName }

        // map manga
        Manga(
            d.id ?: "",
            d.attributes?.title?.en ?: "",
            d.attributes?.description?.en ?: "",
            tags,
            d.attributes?.state ?: "",
            d.attributes?.status ?: "",
            "https://uploads.mangadex.org/covers/${d.id}/$coverArt",
            "https://uploads.mangadex.org/covers/${d.id}/$coverArt.256.jpg"
            )

    }.toList<Manga>()
}