package com.example.proyectofinalopentech.presentation.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.example.composeexample.testUtil.DefaultDispatcherRule
import com.example.proyectofinalopentech.domain.model.Response
import com.example.proyectofinalopentech.domain.model.builders.MangaBuilder
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


class MyMangasViewModelTest{
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val defaultDispatcherRule = DefaultDispatcherRule()

    @MockK(relaxed = true)
    private lateinit var getFavMangasUseCase: GetFavMangasUseCase

    @MockK(relaxed = true)
    private lateinit var setMangaFavUseCase: SetMangaFavUseCase

    @MockK
    lateinit var myMangasViewModel: MyMangasViewModel

    private val expectedMangas = Array(10){MangaBuilder().build()}.toList()
    private val expectedError = "error"
    private val initState = MyMangasUiState(isLoading = false,isError = false, messageError = "", emptyList())
    private val loadingState = MyMangasUiState(isLoading = true,isError = false, messageError = "", emptyList())
    private val errorState = MyMangasUiState(isLoading = true,isError = true, messageError = expectedError, emptyList())
    private val loadedState = MyMangasUiState(isLoading = false,isError = false, messageError = "", expectedMangas)


    @Before
    fun setup(){
        MockKAnnotations.init(this)
        myMangasViewModel = MyMangasViewModel(getFavMangasUseCase,setMangaFavUseCase)

    }

    @Test
    fun `WHEN Get Fav Manga List Use Case return a Manga List EXPECT Init, Loading, Loaded States`() = runTest {


        coEvery { getFavMangasUseCase.invoke() } returns Response.Success(expectedMangas)


        myMangasViewModel.uiState.test {

            // action
            myMangasViewModel.getFavMangas()

            assertEquals(initState, awaitItem())
            assertEquals(loadingState, awaitItem())
            assertEquals(loadedState.copy(favMangas = expectedMangas.reversed()), awaitItem())
        }


    }

    @Test
    fun `WHEN Get fav mangas Use Case return an Error EXPECT Init, Loading, Error States`() = runTest {

        coEvery { getFavMangasUseCase.invoke() } returns Response.Error(expectedError)


        myMangasViewModel.uiState.test {

            // action
            myMangasViewModel.getFavMangas()

            assertEquals(initState, awaitItem())
            assertEquals(loadingState, awaitItem())
            assertEquals(errorState, awaitItem())
        }


    }
}