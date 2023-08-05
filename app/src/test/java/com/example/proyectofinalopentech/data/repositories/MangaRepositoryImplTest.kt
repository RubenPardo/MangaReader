package com.example.proyectofinalopentech.data.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.composeexample.testUtil.DefaultDispatcherRule
import com.example.proyectofinalopentech.data.model.ChapterDTO
import com.example.proyectofinalopentech.data.model.ChapterResponseDTO
import com.example.proyectofinalopentech.data.model.MangaResponseTestDTOBuilder
import com.example.proyectofinalopentech.data.model.VolumeDTO
import com.example.proyectofinalopentech.data.model.mappers.toDomain
import com.example.proyectofinalopentech.data.remote.interfaces.RemoteDataSource
import com.example.proyectofinalopentech.domain.model.Response
import com.example.proyectofinalopentech.domain.repositoryInterfaces.MangaRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class MangaRepositoryImplTest{


    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val defaultDispatcherRule = DefaultDispatcherRule()

    @MockK(relaxed = true)
    private lateinit var remoteDataSource: RemoteDataSource

    private lateinit var mangaRepository: MangaRepository

    @Before
    fun setup(){
        MockKAnnotations.init(this)
        mangaRepository = MangaRepositoryImpl(remoteDataSource)

    }

    @Test
    fun `WHEN get chapters remote datasource throw exception EXPECT repository return a Response Error`() = runTest {

        val message = "Error"
        val mangaId = "1"

        coEvery { remoteDataSource.getChaptersByMangaId(mangaId) } throws Exception(message)

        val response = mangaRepository.getChaptersByMangaId(mangaId)

        coVerify (exactly = 1){ remoteDataSource.getChaptersByMangaId(mangaId) }
        assertEquals(response is Response.Error, true)
        assertEquals(response.message == message, true)

    }

    @Test
    fun `WHEN get chapters remote datasource return Dto EXPECT repository return a Response DO`() = runTest {

        val numVolume = 5
        val numChapters = 20
        val mangaID = "1"

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

        val expectResult = dto.toDomain()

        coEvery { remoteDataSource.getChaptersByMangaId(mangaID) } returns dto

        val response = mangaRepository.getChaptersByMangaId(mangaID)

        coVerify (exactly = 1){ remoteDataSource.getChaptersByMangaId(mangaID) }
        assertEquals(response is Response.Success, true)
        assertEquals(response.data == expectResult, true)

    }

}