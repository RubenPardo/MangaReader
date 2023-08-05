package com.example.proyectofinalopentech.data.model.mappers

import com.example.proyectofinalopentech.common.format
import com.example.proyectofinalopentech.data.model.ChapterDTO
import com.example.proyectofinalopentech.data.model.ChapterResponseDTO
import com.example.proyectofinalopentech.data.model.MangaResponseTestDTOBuilder
import com.example.proyectofinalopentech.data.model.VolumeDTO
import com.example.proyectofinalopentech.domain.BASE_URL_IMAGES
import org.junit.Assert
import org.junit.Test


class VolumeMapperKtTest{
    @Test
    fun `WHEN pass a correct ChapterResponseDTO to domain object EXPECT a list of Volumes objects well formatted`(){

        val numVolume = 5
        val numChapters = 20

        val dto = ChapterResponseDTO(
            result ="ok",
            volumes = Array(numVolume){ index->
                VolumeDTO(
                    name = index.toString(),
                    count = "5",
                    chapters = Array(numChapters) {
                        ChapterDTO(
                            name = it.toString(),
                            id = it.toString()
                        )
                    }.toList()

                )
            }.toList()
        )

        val volumes = dto.toDomain()

        Assert.assertEquals(volumes.size, numVolume)
        volumes.forEachIndexed{index,volume ->
            Assert.assertEquals(volume.chapters.size, numChapters)
            Assert.assertEquals(volume.name, index.toString())
            volume.chapters.forEachIndexed{ i,ch->
                Assert.assertEquals(ch.name, i.toString())
                Assert.assertEquals(ch.id, i.toString())

            }
        }

    }

}