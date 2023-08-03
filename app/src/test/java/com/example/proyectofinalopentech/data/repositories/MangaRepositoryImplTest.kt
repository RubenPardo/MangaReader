package com.example.proyectofinalopentech.data.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.composeexample.testUtil.DefaultDispatcherRule
import com.example.proyectofinalopentech.data.model.MangaResponseTestDTOBuilder
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

  /*  @Test
    fun `WHEN get mangas throw exception EXPECT a Response Error`() = runTest {

        val message = "Error"
        val offset = 0
        val limit = 10

        coEvery { remoteDataSource.getMangaList(offset,limit) } throws Exception(message)

        val response = mangaRepository.getMangas(offset,limit)

        coVerify (exactly = 1){ remoteDataSource.getMangaList(offset,limit) }
        assertEquals(response is Response.Error, true)
        assertEquals(response.message == message, true)

    }

    @Test
    fun `WHEN get mangas return MangaResponseDTO EXPECT a Response Success`() = runTest {

        val offset = 0
        val limit = 10
        val fileName = "FileName"
        val mangaTitle = "Titulo"
        val expectedDescription= "Manga descripcion"

        val mangaDTO = MangaResponseTestDTOBuilder()
            .withNumElements(limit)
            .withTitle(mangaTitle)
            .withDescription(expectedDescription)
            .withFileName(fileName).build()

        coEvery { remoteDataSource.getMangaList(offset,limit) } returns mangaDTO

        val response = mangaRepository.getMangas(offset,limit)

        coVerify (exactly = 1){ remoteDataSource.getMangaList(offset,limit) }
        assertEquals(response is Response.Success, true)
        assertEquals(response.data == mangaDTO.toDomain(), true)

    }*/

}