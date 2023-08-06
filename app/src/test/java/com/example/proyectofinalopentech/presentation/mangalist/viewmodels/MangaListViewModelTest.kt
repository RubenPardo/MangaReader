package com.example.proyectofinalopentech.presentation.mangalist.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.runtime.collectAsState
import androidx.paging.PagingData
import com.example.composeexample.testUtil.DefaultDispatcherRule
import com.example.proyectofinalopentech.data.api.MangaDexApi
import com.example.proyectofinalopentech.data.model.MangaResponseTestDTOBuilder
import com.example.proyectofinalopentech.data.remote.implementation.RemoteDataSourceRetrofit
import com.example.proyectofinalopentech.data.remote.interfaces.RemoteDataSource
import com.example.proyectofinalopentech.data.repositories.MangaRepositoryImpl
import com.example.proyectofinalopentech.domain.model.Manga
import com.example.proyectofinalopentech.domain.repositoryInterfaces.MangaRepository
import com.example.proyectofinalopentech.domain.usecases.GetMangaChaptersByIdTest
import com.example.proyectofinalopentech.domain.usecases.GetMangasByNameUseCase
import com.example.proyectofinalopentech.presentation.viewmodels.MangaListViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class MangaListViewModelTest{
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val defaultDispatcherRule = DefaultDispatcherRule()


    @MockK(relaxed = true)
    private lateinit var mangaDexApi: MangaDexApi

    private lateinit var remoteDataSource: RemoteDataSource


    private lateinit var getMangasByNameUseCase: GetMangasByNameUseCase

    private lateinit var mangaRepository: MangaRepository

    @MockK
    lateinit var mangaListViewModel: MangaListViewModel


    @Before
    fun setup(){
        MockKAnnotations.init(this)
        remoteDataSource = RemoteDataSourceRetrofit(mangaDexApi)
        mangaRepository = MangaRepositoryImpl(remoteDataSource)
        getMangasByNameUseCase = GetMangasByNameUseCase(mangaRepository)
        mangaListViewModel = MangaListViewModel(getMangasByNameUseCase)

    }

    @Test
    fun `WHEN call mangaListViewModel get() first time EXPECT a new FLOW of Paging data manga`() = runTest{

        val numMangas = 15
        val fileName = "FileName"
        val mangaTitle = "Titulo"
        val expectedDescription= "Manga descripcion"

        val mangaDTO = MangaResponseTestDTOBuilder()
            .withNumElements(numMangas)
            .withTitle(mangaTitle)
            .withDate("2023-08-01T15:47:38+00:00",)
            .withDescription(expectedDescription)
            .withFileName(fileName).build()

        coEvery { mangaDexApi.getMangas("",0,0) } returns mangaDTO

       val res = mangaListViewModel.get("")

    }
}