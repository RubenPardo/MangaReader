package com.example.proyectofinalopentech.domain.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.composeexample.testUtil.DefaultDispatcherRule
import com.example.proyectofinalopentech.domain.model.Chapter
import com.example.proyectofinalopentech.domain.model.Response
import com.example.proyectofinalopentech.domain.repositoryInterfaces.MangaRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetMangaChaptersByIdTest{

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val defaultDispatcherRule = DefaultDispatcherRule()

    @MockK(relaxed = true)
    private lateinit var mangaRepository: MangaRepository

    private lateinit var getMangasUseCase: GetMangaChaptersByIdUseCase

    @Before
    fun setup(){
        MockKAnnotations.init(this)
        getMangasUseCase = GetMangaChaptersByIdUseCase(mangaRepository)
    }

    @Test
     fun `WHEN repository return success use case returns success`() = runTest {

         val id = "1"
         val chaptersExpected = Array(4){Chapter(it.toString(), it.toString())}.toList()

         coEvery { mangaRepository.getChaptersByMangaId(id) } returns Response.Success(chaptersExpected)

        val response = getMangasUseCase.invoke(id)

         coVerify (exactly = 1) { mangaRepository.getChaptersByMangaId(id) }
         assertEquals(response is Response.Success, true)
         assertEquals(response.data == chaptersExpected, true)

     }

     @Test
     fun `WHEN repository return error use case returns error`() = runTest {

         val id = "1"
         val error = "error"

         coEvery { mangaRepository.getChaptersByMangaId(id) } returns Response.Error(error)

         val response = getMangasUseCase.invoke(id)

         coVerify (exactly = 1) { mangaRepository.getChaptersByMangaId(id) }
         assertEquals(response is Response.Error, true)
         assertEquals(response.message == error, true)

     }
}