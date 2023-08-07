package com.example.proyectofinalopentech.domain.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.composeexample.testUtil.DefaultDispatcherRule
import com.example.proyectofinalopentech.domain.model.ChapterDetail
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

class GetChapterDetailUseCaseTest{
     @get:Rule
     val instantExecutorRule = InstantTaskExecutorRule()

     @get:Rule
     val defaultDispatcherRule = DefaultDispatcherRule()

     @MockK(relaxed = true)
     private lateinit var mangaRepository: MangaRepository

     private lateinit var getChapterDetailUseCase: GetChapterDetailUseCase

     @Before
     fun setup(){
         MockKAnnotations.init(this)
         getChapterDetailUseCase = GetChapterDetailUseCase(mangaRepository)
     }


     @Test
     fun`WHEN manga repository returns aaa success  Expect return a success`() = runTest {

         val chapterId = "1"
         val chapterRes = ChapterDetail(emptyList())


         coEvery { mangaRepository.getChapterDetail(chapterId) } returns Response.Success(chapterRes)

         val response = getChapterDetailUseCase(chapterId)

         coVerify (exactly = 1) { mangaRepository.getChapterDetail(chapterId) }
         TestCase.assertEquals(response is Response.Success, true)
         TestCase.assertEquals(response.data == chapterRes, true)

     }

     @Test
     fun`WHEN manga repository returns a error and is fav success Expect a Response Error`() = runTest {

         val error = "error"
         val chapterId = "1"


         coEvery { mangaRepository.getChapterDetail(chapterId) } returns Response.Error(error)

         val response = getChapterDetailUseCase(chapterId)

         coVerify (exactly = 1) { mangaRepository.getChapterDetail(chapterId) }
         TestCase.assertEquals(response is Response.Error, true)
         TestCase.assertEquals(response.message == error, true)

     }

 }