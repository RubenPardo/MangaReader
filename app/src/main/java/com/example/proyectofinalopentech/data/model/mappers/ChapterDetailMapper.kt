package com.example.proyectofinalopentech.data.model.mappers

import com.example.proyectofinalopentech.data.model.ChapterDetailDTO
import com.example.proyectofinalopentech.data.model.ChapterDetailResponseDTO
import com.example.proyectofinalopentech.domain.model.ChapterDetail
import com.example.proyectofinalopentech.domain.model.MangaPage

fun ChapterDetailResponseDTO.toDomain():ChapterDetail {
    if(result == "error") throw Exception()
    // https://uploads.mangadex.org/data-saver/hash/:imagen.jpg
    return ChapterDetail(chapter?.data?.mapIndexed { index, url ->
        MangaPage(
            pageHR = "${baseUrl}/data/${chapter?.hash}/${url}",
            pageLR = "${baseUrl}/data-saver/${chapter?.hash}/${chapter?.dataSaver!![index]}",
        )
    }?.toList() ?: emptyList())
}