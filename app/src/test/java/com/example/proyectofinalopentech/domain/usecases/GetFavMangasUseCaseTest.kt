package com.example.proyectofinalopentech.domain.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.composeexample.testUtil.DefaultDispatcherRule
import com.example.proyectofinalopentech.domain.model.Manga
import com.example.proyectofinalopentech.domain.model.MangaPage
import com.example.proyectofinalopentech.domain.model.Response
import com.example.proyectofinalopentech.domain.model.builders.MangaBuilder
import com.example.proyectofinalopentech.domain.repositoryInterfaces.MangaRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetFavMangasUseCaseTest{

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val defaultDispatcherRule = DefaultDispatcherRule()

    @MockK(relaxed = true)
    private lateinit var mangaRepository: MangaRepository

    private lateinit var getFavMangaUseCaseTest: GetFavMangasUseCase

    @Before
    fun setup(){
        MockKAnnotations.init(this)
        getFavMangaUseCaseTest = GetFavMangasUseCase(mangaRepository)
    }

    @Test
    fun `WHEN manga repo return success EXPECT a success but reversed data`() = runTest {
        val responseRepoMangas: List<Manga> = Array(5){MangaBuilder().withTtitle(it.toString()).build()}.toList()
        val expectedMangas = responseRepoMangas.reversed()
        coEvery { mangaRepository.getFavMangas() } returns Response.Success(responseRepoMangas)


        val res =  getFavMangaUseCaseTest()

        coVerify (exactly = 1) { mangaRepository.getFavMangas() }

        TestCase.assertEquals(res is Response.Success,true)
        TestCase.assertEquals(res.data == expectedMangas,true)
    }

    @Test
    fun `WHEN manga repo return error EXPECT a error`() = runTest {
        val error =""
        coEvery { mangaRepository.getFavMangas() } returns Response.Error(error)


        val res =  getFavMangaUseCaseTest()

        coVerify (exactly = 1) { mangaRepository.getFavMangas() }

        TestCase.assertEquals(res is Response.Error,true)
        TestCase.assertEquals(res.message == error,true)
    }

}