package com.example.proyectofinalopentech.data.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.composeexample.testUtil.DefaultDispatcherRule
import com.example.proyectofinalopentech.data.local.interfaces.LocalDataSource
import com.example.proyectofinalopentech.data.local.model.MangaLocal
import com.example.proyectofinalopentech.data.model.ChapterDTO
import com.example.proyectofinalopentech.data.model.ChapterDetailDTO
import com.example.proyectofinalopentech.data.model.ChapterDetailResponseDTO
import com.example.proyectofinalopentech.data.model.ChapterResponseDTO
import com.example.proyectofinalopentech.data.model.MangaResponseTestDTOBuilder
import com.example.proyectofinalopentech.data.model.VolumeDTO
import com.example.proyectofinalopentech.data.model.mappers.toDomain
import com.example.proyectofinalopentech.data.model.mappers.toLocal
import com.example.proyectofinalopentech.data.remote.interfaces.RemoteDataSource
import com.example.proyectofinalopentech.domain.model.Response
import com.example.proyectofinalopentech.domain.model.builders.MangaBuilder
import com.example.proyectofinalopentech.domain.repositoryInterfaces.MangaRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.any


class MangaRepositoryImplTest{


    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val defaultDispatcherRule = DefaultDispatcherRule()

    @MockK(relaxed = true)
    private lateinit var remoteDataSource: RemoteDataSource

    @MockK(relaxed = true)
    private lateinit var localDataSource: LocalDataSource

    private lateinit var mangaRepository: MangaRepository

    @Before
    fun setup(){
        MockKAnnotations.init(this)
        mangaRepository = MangaRepositoryImpl(remoteDataSource,localDataSource)

    }

    @Test
    fun `WHEN get chapters remote datasource throw exception EXPECT repository return a Response Error`() = runTest {

        val message = "Error"
        val mangaId = "1"

        coEvery { remoteDataSource.getChaptersByMangaId(mangaId) } throws Exception(message)

        val response = mangaRepository.getChaptersByMangaId(mangaId)

        coVerify (exactly = 1){ remoteDataSource.getChaptersByMangaId(mangaId) }
        assertEquals(response is Response.Error, true)
        assertEquals(response.message == message, true)

    }

    @Test
    fun `WHEN get chapters remote datasource return Dto EXPECT repository return a Response DO`() = runTest {

        val numVolume = 5
        val numChapters = 20
        val mangaID = "1"

        val dto = ChapterResponseDTO(
            result ="ok",
            volumes = Array(numVolume){ index->
                VolumeDTO(
                    name = index.toString(),
                    count = "5",
                    chapters = Array(numChapters) {
                        ChapterDTO(
                            name = it.toString(),
                            id = it.toString()
                        )
                    }.toList()

                )
            }.toList()
        )

        val expectResult = dto.toDomain()

        coEvery { remoteDataSource.getChaptersByMangaId(mangaID) } returns dto

        val response = mangaRepository.getChaptersByMangaId(mangaID)

        coVerify (exactly = 1){ remoteDataSource.getChaptersByMangaId(mangaID) }
        assertEquals(response is Response.Success, true)
        assertEquals(response.data == expectResult, true)

    }


    @Test
    fun `WHEN get fav manga local datasource return MangaLocal EXPECT repository return a Response Manga `() = runTest {

        val numMangas = 10
        val mangaID = "1"
        val mangaTitle = "title"
        val mangaUrl = "title"

        val favLocalMangas = Array(numMangas){MangaLocal(mangaID,mangaTitle,mangaUrl)}.toList()

        val expectResult = favLocalMangas.map { it.toDomain() }

        coEvery { localDataSource.getFavMangas() } returns favLocalMangas

        val response = mangaRepository.getFavMangas()

        coVerify (exactly = 1){ localDataSource.getFavMangas() }
        assertEquals(response is Response.Success, true)
        assertEquals(response.data == expectResult, true)

    }

    @Test
    fun `WHEN get fav manga local datasource throw error EXPECT repository return a Response Error `() = runTest {

        val error = "error"

        coEvery { localDataSource.getFavMangas() } throws  Exception(error)

        val response = mangaRepository.getFavMangas()

        coVerify (exactly = 1){ localDataSource.getFavMangas() }
        assertEquals(response is Response.Error, true)
        assertEquals(response.message == error, true)

    }

    @Test
    fun `WHEN set fav manga local datasource returns greather than 1 EXPECT repository return a Response Succes `() = runTest {

        val mangaToInsert = MangaBuilder().build()
        val mangaToInsertLocal = mangaToInsert.toLocal()

        coEvery { localDataSource.insertFavManga(mangaToInsertLocal) } returns 2L

        val response = mangaRepository.saveFavManga(mangaToInsert)

        coVerify (exactly = 1){ localDataSource.insertFavManga(mangaToInsertLocal) }
        assertEquals(response is Response.Success, true)
        assertEquals(response.data, true)

    }

    @Test
    fun `WHEN set fav manga local datasource throw error EXPECT repository return a Response Error `() = runTest {

        val error = "error"

        val mangaToInsert = MangaBuilder().build()
        val mangaToInsertLocal = mangaToInsert.toLocal()

        coEvery { localDataSource.insertFavManga(mangaToInsertLocal) }  throws Exception(error)

        val response = mangaRepository.saveFavManga(mangaToInsert)

        coVerify (exactly = 1){ localDataSource.insertFavManga(mangaToInsertLocal) }
        assertEquals(response is Response.Error, true)
        assertEquals(response.message == error, true)

    }

    @Test
    fun `WHEN remove fav manga local datasource returns nothing EXPECT repository return a Response Succes `() = runTest {

        val mangaToRemove = MangaBuilder().build()
        val mangaToRemoveLocal = mangaToRemove.toLocal()

        coEvery { localDataSource.removeFavManga(mangaToRemoveLocal) } returns Unit

        val response = mangaRepository.removeFavManga(mangaToRemove)

        println(response)

        coVerify (exactly = 1){ localDataSource.removeFavManga(mangaToRemoveLocal) }
        assertEquals(response is Response.Success, true)
        assertEquals(response.data, true)

    }

    @Test
    fun `WHEN remove fav manga local datasource throw error EXPECT repository return a Response Error `() = runTest {

        val error = "error"

        val mangaToRemove = MangaBuilder().build()
        val mangaToRemoveLocal = mangaToRemove.toLocal()

        coEvery { localDataSource.removeFavManga(mangaToRemoveLocal) }  throws Exception(error)

        val response = mangaRepository.removeFavManga(mangaToRemove)

        coVerify (exactly = 1){ localDataSource.removeFavManga(mangaToRemoveLocal) }
        assertEquals(response is Response.Error, true)
        assertEquals(response.message == error, true)

    }

    @Test
    fun `WHEN is fav manga local datasource return ToF EXPECT repository return a Response Succes `() = runTest {

        val error = "error"

        val manga = MangaBuilder().build()
        val mangaLocal = manga.toLocal()

        coEvery { localDataSource.isFavManga(mangaLocal.id) }  returns true

        val response = mangaRepository.isFavManga(manga.id)

        coVerify (exactly = 1){ localDataSource.isFavManga(mangaLocal.id) }
        assertEquals(response is Response.Success, true)
        assertEquals(response.data == true, true)

    }

    @Test
    fun `WHEN is fav manga local datasource throw Error EXPECT repository return a Response Error `() = runTest {

        val error = "error"

        val manga = MangaBuilder().build()
        val mangaLocal = manga.toLocal()

        coEvery { localDataSource.isFavManga(mangaLocal.id) }  throws  Exception(error)

        val response = mangaRepository.isFavManga(manga.id)

        coVerify (exactly = 1){ localDataSource.isFavManga(mangaLocal.id) }
        assertEquals(response is Response.Error, true)
        assertEquals(response.message == error, true)

    }

    @Test
    fun `WHEN get chapter detail remote datasource return ChapterDetailDto EXPECT repository return a Response Success with a DO `() = runTest {

        val chapterId = "1"

        val numPages = 5
        val chapterDetailDto = ChapterDetailResponseDTO("","baseURl",
            ChapterDetailDTO(
                "hash",
                Array(numPages){it.toString()}.toList(),
                Array(numPages){it.toString()}.toList(),
            )
        )

        val expectedChapterDetail = chapterDetailDto.toDomain()

        coEvery { remoteDataSource.getChapterDetail(chapterId) }  returns chapterDetailDto

        val response = mangaRepository.getChapterDetail(chapterId)

        coVerify (exactly = 1){ remoteDataSource.getChapterDetail(chapterId)}
        assertEquals(response is Response.Success, true)
        assertEquals(response.data == expectedChapterDetail, true)

    }

    @Test
    fun `WHEN get chapter detail remote datasource throw Exception EXPECT repository return a Response Error `() = runTest {

        val chapterId = "1"
        val error = "1"

        val numPages = 5
        val chapterDetailDto = ChapterDetailResponseDTO("","baseURl",
            ChapterDetailDTO(
                "hash",
                Array(numPages){it.toString()}.toList(),
                Array(numPages){it.toString()}.toList(),
            )
        )


        coEvery { remoteDataSource.getChapterDetail(chapterId) }  throws Exception(error)

        val response = mangaRepository.getChapterDetail(chapterId)


        coVerify (exactly = 1){  remoteDataSource.getChapterDetail(chapterId) }
        assertEquals(response is Response.Error, true)
        assertEquals(response.message == error, true)

    }

}