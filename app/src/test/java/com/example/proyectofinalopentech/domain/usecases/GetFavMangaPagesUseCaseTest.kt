package com.example.proyectofinalopentech.domain.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.composeexample.testUtil.DefaultDispatcherRule
import com.example.proyectofinalopentech.domain.model.MangaPage
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

class GetFavMangaPagesUseCaseTest{
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val defaultDispatcherRule = DefaultDispatcherRule()

    @MockK(relaxed = true)
    private lateinit var mangaRepository: MangaRepository

    private lateinit var getFavMangaPagesUseCaseTest: GetFavMangaPagesUseCase

    @Before
    fun setup(){
        MockKAnnotations.init(this)
        getFavMangaPagesUseCaseTest = GetFavMangaPagesUseCase(mangaRepository)
    }

    @Test
    fun `WHEN manga repo return success EXPECT a success but reversed data`() = runTest {
        val responseRepoPages: List<MangaPage> = listOf(MangaPage("","",false),MangaPage("","",true))
        val expectedPages = responseRepoPages.reversed()
        coEvery { mangaRepository.getFavMangaPages() } returns Response.Success(responseRepoPages)


        val res =  getFavMangaPagesUseCaseTest()

        coVerify (exactly = 1) { mangaRepository.getFavMangaPages() }

        assertEquals(res is Response.Success,true)
        assertEquals(res.data == expectedPages,true)
    }

    @Test
    fun `WHEN manga repo return error EXPECT a error`() = runTest {
        val error =""
        coEvery { mangaRepository.getFavMangaPages() } returns Response.Error(error)


        val res =  getFavMangaPagesUseCaseTest()

        coVerify (exactly = 1) { mangaRepository.getFavMangaPages() }

        assertEquals(res is Response.Error,true)
        assertEquals(res.message == error,true)
    }
}