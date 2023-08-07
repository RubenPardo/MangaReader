package com.example.proyectofinalopentech.domain.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.composeexample.testUtil.DefaultDispatcherRule
import com.example.proyectofinalopentech.domain.model.Response
import com.example.proyectofinalopentech.domain.model.builders.MangaBuilder
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


class SetMangaFavUseCaseTest{
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val defaultDispatcherRule = DefaultDispatcherRule()

    @MockK(relaxed = true)
    private lateinit var mangaRepository: MangaRepository

    private lateinit var setMangaFavUseCase: SetMangaFavUseCase

    @Before
    fun setup(){
        MockKAnnotations.init(this)
        setMangaFavUseCase = SetMangaFavUseCase(mangaRepository)
    }

     @Test
     fun `WHEN call use case with a manga is Fav = false EXPECT make a call to repository saveFav`() = runTest {

        val manga = MangaBuilder().build().copy(isFav = false)
         coEvery { mangaRepository.saveFavManga(manga) } returns Response.Success(true)

        val response = setMangaFavUseCase.invoke(manga)

         coVerify (exactly = 1) { mangaRepository.saveFavManga(manga) }
         coVerify (exactly = 0) { mangaRepository.removeFavManga(manga) }
         assertEquals(response is Response.Success, true)
         assertEquals(response.data, true)

     }

    @Test
    fun `WHEN call use case with a manga is Fav = true EXPECT make a call to repository removeFav`() = runTest {

        val manga = MangaBuilder().build().copy(isFav = true)
        coEvery { mangaRepository.removeFavManga(manga) } returns Response.Success(true)

        val response = setMangaFavUseCase.invoke(manga)

        coVerify (exactly = 0) { mangaRepository.saveFavManga(manga) }
        coVerify (exactly = 1) { mangaRepository.removeFavManga(manga) }
        assertEquals(response is Response.Success, true)
        assertEquals(response.data, true)

    }
}