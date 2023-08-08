package com.example.proyectofinalopentech.presentation.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.example.composeexample.testUtil.DefaultDispatcherRule
import com.example.proyectofinalopentech.domain.model.MangaPage
import com.example.proyectofinalopentech.domain.model.Response
import com.example.proyectofinalopentech.domain.usecases.GetFavMangaPagesUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MyPagesViewModelTest{
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val defaultDispatcherRule = DefaultDispatcherRule()

    @MockK(relaxed = true)
    private lateinit var getFavMangaPagesUseCase: GetFavMangaPagesUseCase


    @MockK
    lateinit var myPagesViewModel: MyPagesViewModel

    private val pagesExpected = emptyList<MangaPage>()
    private val expectedError = "error"
    private val initState = MyPagesUiState(isLoading = false,isError = false, messageError = "", emptyList())
    private val loadingState = MyPagesUiState(isLoading = true,isError = false, messageError = "", emptyList())
    private val errorState = MyPagesUiState(isLoading = false,isError = true, messageError = expectedError, emptyList())
    private val loadedState = MyPagesUiState(isLoading = false,isError = false, messageError = "", pagesExpected)


    @Before
    fun setup(){
        MockKAnnotations.init(this)
        myPagesViewModel = MyPagesViewModel(getFavMangaPagesUseCase)

    }

    @Test
    fun `WHEN Get chapter detail  Use Case return a ChapterDetail EXPECT Init, Loading, Loaded States`() = runTest {


        coEvery { getFavMangaPagesUseCase.invoke() } returns Response.Success(pagesExpected)


        myPagesViewModel.uiState.test {

            // action
            myPagesViewModel.getFavPanels()

            TestCase.assertEquals(initState, awaitItem())
            TestCase.assertEquals(loadingState, awaitItem())
            TestCase.assertEquals(loadedState, awaitItem())
        }


    }

    @Test
    fun `WHEN Get chapter details Use Case return an Error EXPECT Init, Loading, Error States`() = runTest {

        coEvery { getFavMangaPagesUseCase.invoke() } returns Response.Error(expectedError)


        myPagesViewModel.uiState.test {

            // action
            myPagesViewModel.getFavPanels()

            TestCase.assertEquals(initState, awaitItem())
            TestCase.assertEquals(loadingState, awaitItem())
            TestCase.assertEquals(errorState, awaitItem())
        }


    }
}