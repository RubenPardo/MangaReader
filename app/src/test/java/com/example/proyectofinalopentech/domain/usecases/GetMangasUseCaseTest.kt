package com.example.proyectofinalopentech.domain.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.composeexample.testUtil.DefaultDispatcherRule
import com.example.proyectofinalopentech.data.remote.interfaces.RemoteDataSource
import com.example.proyectofinalopentech.domain.model.Manga
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


class GetMangasUseCaseTest{


    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val defaultDispatcherRule = DefaultDispatcherRule()

    @MockK(relaxed = true)
    private lateinit var mangaRepository: MangaRepository

    private lateinit var getMangasUseCase: GetMangasUseCase

    @Before
    fun setup(){
        MockKAnnotations.init(this)
        getMangasUseCase = GetMangasUseCase(mangaRepository)
    }

    @Test
    fun `WHEN repository return success use case returns success`() = runTest {

        val offset = 0
        val limit = 10
        val mangaExpected = listOf(Manga(id = "1",title = "a", description = "b", tags = listOf("Drama"), status = "ongoing", state = "", fullImageUrl = "", smallImageUrl = ""))

        coEvery { mangaRepository.getMangas(offset, limit) } returns Response.Success(mangaExpected)

       val response = getMangasUseCase.invoke(offset, limit)

        coVerify (exactly = 1) { mangaRepository.getMangas(offset, limit) }
        assertEquals(response is Response.Success, true)
        assertEquals(response.data == mangaExpected, true)

    }

    @Test
    fun `WHEN repository return error use case returns error`() = runTest {

        val offset = 0
        val limit = 10
        val error = "Error"

        coEvery { mangaRepository.getMangas(offset, limit) } returns Response.Error(error)

        val response = getMangasUseCase.invoke(offset, limit)

        coVerify (exactly = 1) { mangaRepository.getMangas(offset, limit) }
        assertEquals(response is Response.Error, true)
        assertEquals(response.message == error, true)

    }
}