package com.example.proyectofinalopentech.data.model.mappers

import com.example.proyectofinalopentech.data.model.ChapterResponseDTO
import com.example.proyectofinalopentech.domain.model.Chapter
import com.example.proyectofinalopentech.domain.model.Volume

fun ChapterResponseDTO.toDomain(): List<Volume>{
    return volumes.map { volume ->

        Volume(
            name = volume.name ?: "",
            // TODO
            chapters = volume.chapters.map { chapter ->
                Chapter(
                    chapter.name ?: "",
                    chapter.id ?: "",
                )
            }.toList()
        )

    }.toList()
}