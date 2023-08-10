package com.example.proyectofinalopentech.domain.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.composeexample.testUtil.DefaultDispatcherRule
import com.example.proyectofinalopentech.domain.model.MangaPage
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


class SetMangaPageFavUseCaseTest{
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val defaultDispatcherRule = DefaultDispatcherRule()

    @MockK(relaxed = true)
    private lateinit var mangaRepository: MangaRepository

    private lateinit var setMangaPageFavUseCase: SetMangaPageFavUseCase

    @Before
    fun setup(){
        MockKAnnotations.init(this)
        setMangaPageFavUseCase = SetMangaPageFavUseCase(mangaRepository)
    }

     @Test
     fun `WHEN call use case with a manga is Fav = false EXPECT make a call to repository saveFav`() = runTest {

        val page = MangaPage("","","",false,0)
         coEvery { mangaRepository.saveMangaPage(page) } returns Response.Success(true)

        val response = setMangaPageFavUseCase.invoke(page)

         coVerify (exactly = 1) { mangaRepository.saveMangaPage(page) }
         coVerify (exactly = 0) { mangaRepository.removeMangaPage(page) }
         assertEquals(response is Response.Success, true)
         assertEquals(response.data, true)

     }

    @Test
    fun `WHEN call use case with a manga is Fav = true EXPECT make a call to repository removeFav`() = runTest {

        val page = MangaPage("","","",true,0)
        coEvery { mangaRepository.removeMangaPage(page) } returns Response.Success(true)

        val response = setMangaPageFavUseCase.invoke(page)

        coVerify (exactly = 0) { mangaRepository.saveMangaPage(page) }
        coVerify (exactly = 1) { mangaRepository.removeMangaPage(page) }
        assertEquals(response is Response.Success, true)
        assertEquals(response.data, true)

    }
}