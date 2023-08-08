package com.example.proyectofinalopentech.presentation.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.example.composeexample.testUtil.DefaultDispatcherRule
import com.example.proyectofinalopentech.domain.model.ChapterDetail
import com.example.proyectofinalopentech.domain.model.Response
import com.example.proyectofinalopentech.domain.model.builders.MangaBuilder
import com.example.proyectofinalopentech.domain.usecases.GetChapterDetailUseCase
import com.example.proyectofinalopentech.domain.usecases.GetFavMangasUseCase
import com.example.proyectofinalopentech.domain.usecases.SetMangaFavUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class ChapterReaderViewModelTest{
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val defaultDispatcherRule = DefaultDispatcherRule()

    @MockK(relaxed = true)
    private lateinit var getChapterDetailUseCase: GetChapterDetailUseCase

    @MockK(relaxed = true)
    private lateinit var setMangaFavUseCase: SetMangaFavUseCase

    @MockK
    lateinit var chapterReaderViewModel: ChapterReaderViewModel

    private val chapterExpected = ChapterDetail(listPageUrls = emptyList())
    private val expectedError = "error"
    private val initState = ChapterReaderUiState(isLoading = false,isError = false, messageError = "", null)
    private val loadingState = ChapterReaderUiState(isLoading = true,isError = false, messageError = "", null)
    private val errorState = ChapterReaderUiState(isLoading = false,isError = true, messageError = expectedError, null)
    private val loadedState = ChapterReaderUiState(isLoading = false,isError = false, messageError = "", chapterExpected)


    @Before
    fun setup(){
        MockKAnnotations.init(this)
        chapterReaderViewModel = ChapterReaderViewModel(getChapterDetailUseCase)

    }

    @Test
    fun `WHEN Get chapter detail  Use Case return a ChapterDetail EXPECT Init, Loading, Loaded States`() = runTest {


        coEvery { getChapterDetailUseCase.invoke("") } returns Response.Success(chapterExpected)


        chapterReaderViewModel.uiState.test {

            // action
            chapterReaderViewModel.getChapterDetail("")

            assertEquals(initState, awaitItem())
            assertEquals(loadingState, awaitItem())
            assertEquals(loadedState, awaitItem())
        }


    }

    @Test
    fun `WHEN Get chapter details Use Case return an Error EXPECT Init, Loading, Error States`() = runTest {

        coEvery { getChapterDetailUseCase.invoke("") } returns Response.Error(expectedError)


        chapterReaderViewModel.uiState.test {

            // action
            chapterReaderViewModel.getChapterDetail("")

            assertEquals(initState, awaitItem())
            assertEquals(loadingState, awaitItem())
            assertEquals(errorState, awaitItem())
        }


    }
}