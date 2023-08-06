package com.example.proyectofinalopentech.data.model.mappers

import com.example.proyectofinalopentech.data.model.ChapterDTO
import com.example.proyectofinalopentech.data.model.ChapterResponseDTO
import com.example.proyectofinalopentech.domain.model.Chapter

fun ChapterResponseDTO.toDomain(): List<Chapter>{

    val allChapterDto = mutableListOf<ChapterDTO>()
    volumes.forEach { allChapterDto.addAll(it.chapters) }
    return allChapterDto.map { dto ->
        Chapter(
            name = dto.name ?: "",
            id = dto.id ?: ""
        )
    }.toList()
}