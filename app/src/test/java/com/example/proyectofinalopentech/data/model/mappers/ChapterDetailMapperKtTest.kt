package com.example.proyectofinalopentech.data.model.mappers

import com.example.proyectofinalopentech.data.model.ChapterDetailDTO
import com.example.proyectofinalopentech.data.model.ChapterDetailResponseDTO
import org.junit.Assert
import org.junit.Test


class ChapterDetailMapperKtTest{

    private val baseURl = "baseurl"
    private val hash = "hash"
    private val data = "data"
    private val dataSaver = "data-saver"


    @Test
    fun `WHEN ChapterDetailResponseDTO well formatted call toDomain EXPECT ChapterDetail`(){

        val numPages = 5
        val dto = ChapterDetailResponseDTO("",baseURl,
            ChapterDetailDTO(
                hash,
                Array(numPages){it.toString()}.toList(),
                Array(numPages){it.toString()}.toList(),
            ))

        val res = dto.toDomain()

        Assert.assertEquals(res.listPageUrls.size, numPages)
        res.listPageUrls.forEachIndexed{index,page ->
            Assert.assertEquals(page.pageHR, "$baseURl/${data}/${hash}/${index.toString()}")
            Assert.assertEquals(page.pageLR, "$baseURl/${dataSaver}/${hash}/${index.toString()}")
        }
    }
}
