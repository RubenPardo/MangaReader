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


class GetMangaInfoUseCaseTest{

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val defaultDispatcherRule = DefaultDispatcherRule()

    @MockK(relaxed = true)
    private lateinit var mangaRepository: MangaRepository

    private lateinit var getMangaInfoUseCase: GetMangaInfoUseCase

    @Before
    fun setup(){
        MockKAnnotations.init(this)
        getMangaInfoUseCase = GetMangaInfoUseCase(mangaRepository)
    }


    @Test
    fun`WHEN manga repository returns a manga and is fav false Expect a Manga with is Fav atr with false`() = runTest {

        val mangaId = "1"
        val mangaRes = MangaBuilder().build()
        val isFavRes = false

        val mangaExpected = mangaRes.copy(isFav=isFavRes)

        coEvery { mangaRepository.getMangaInfo(mangaId) } returns Response.Success(mangaRes)
        coEvery { mangaRepository.isFavManga(mangaId) } returns Response.Success(isFavRes)

        val mangaResponseTest = getMangaInfoUseCase(mangaId)

        coVerify (exactly = 1) { mangaRepository.getMangaInfo(mangaId) }
        coVerify (exactly = 1) { mangaRepository.isFavManga(mangaId) }
        assertEquals(mangaResponseTest is Response.Success, true)
        assertEquals(mangaResponseTest.data == mangaExpected, true)

    }

    @Test
    fun`WHEN manga repository returns a error and is fav success Expect a Response Error`() = runTest {

        val mangaId = "1"
        val error = "error dfsfsf"
        val isFavRes = false


        coEvery { mangaRepository.getMangaInfo(mangaId) } returns Response.Error(error)
        coEvery { mangaRepository.isFavManga(mangaId) } returns Response.Success(isFavRes)

        val mangaResponseTest = getMangaInfoUseCase(mangaId)

        coVerify (exactly = 1) { mangaRepository.getMangaInfo(mangaId) }
        coVerify (exactly = 0) { mangaRepository.isFavManga(mangaId) }
        assertEquals(mangaResponseTest is Response.Error, true)
        assertEquals(mangaResponseTest.message == error, true)

    }

    @Test
    fun`WHEN manga repository returns a success and is fav an error Expect a Response Error`() = runTest {

        val mangaId = "1"
        val error = "error"
        val isFavRes = false
        val mangaRes = MangaBuilder().build()

        coEvery { mangaRepository.getMangaInfo(mangaId) } returns Response.Success(mangaRes)
        coEvery { mangaRepository.isFavManga(mangaId) } returns Response.Error(error)

        val mangaResponseTest = getMangaInfoUseCase(mangaId)

        coVerify (exactly = 1) { mangaRepository.getMangaInfo(mangaId) }
        coVerify (exactly = 1) { mangaRepository.isFavManga(mangaId) }
        assertEquals(mangaResponseTest is Response.Error, true)
        assertEquals(mangaResponseTest.message == error, true)

    }


}