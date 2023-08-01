package com.example.proyectofinalopentech.data.model.mappers

import com.example.proyectofinalopentech.data.model.MangaResponseTestDTOBuilder
import com.example.proyectofinalopentech.domain.BASE_URL_IMAGES
import org.junit.Assert.assertEquals
import org.junit.Test


class MangaMapperKtTest{

    @Test
    fun `WHEN pass a correct MangaDTO to domain object EXPECT a list of Manga objects well formatted`(){

        val numMangas = 15
        val fileName = "FileName"
        val mangaTitle = "Titulo"
        val expectedDescription= "Manga descripcion"

        val mangaDTO = MangaResponseTestDTOBuilder()
            .withNumElements(numMangas)
            .withTitle(mangaTitle)
            .withDescription(expectedDescription)
            .withFileName(fileName).build()

        val mangas = mangaDTO.toDomain()

        assertEquals(mangas.size,numMangas)
        mangas.forEachIndexed{index,manga ->
            assertEquals(manga.title,  "$mangaTitle $index")
            assertEquals(manga.description,  expectedDescription)
            assertEquals(manga.tags.size,  1)
            assertEquals(manga.fullImageUrl,  "$BASE_URL_IMAGES${manga.id}/$fileName")
        }

    }


}