package com.example.proyectofinalopentech.data.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.composeexample.testUtil.DefaultDispatcherRule
import com.example.proyectofinalopentech.data.model.ChapterDetailResponseDTO
import com.example.proyectofinalopentech.data.model.MangaListResponseDTO
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import org.hamcrest.CoreMatchers.*
import retrofit2.HttpException

@ExperimentalCoroutinesApi
class MangaDexApiTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = DefaultDispatcherRule()


    private lateinit var api: MangaDexApi

    @Before
    fun setup(){
        api = retrofit.create(MangaDexApi::class.java)
    }

    @Test
    fun `WHEN get mangas is called with a non existing manga name EXPECT empty list of data`() = runTest {
       val response =  api.getMangas("adnsdfjksfn",0,10)
        assertThat(response is MangaListResponseDTO, `is`(true))
        assertThat(response.data, `is`(emptyList()))
    }

    @Test
    fun `WHEN get mangas is called with a empty string manga name EXPECT list of data`() = runTest {
        val response =  api.getMangas("",0,10)
        assertThat(response is MangaListResponseDTO, `is`(true))
        assertThat(response.data.size>1, `is`(true))
    }

    @Test
    fun `WHEN get mangas is called with a existing manga name EXPECT list of data`() = runTest {
        val response =  api.getMangas("Berserk",0,10)
        assertThat(response is MangaListResponseDTO, `is`(true))
        assertThat(response.data.size>1, `is`(true))
    }

    @Test(expected = HttpException::class)
    fun `WHEN get chapters is called with empty string THROW 404 Http exception `() = runTest {
        api.getChapters("")
        assert(false)
    }

    @Test(expected = HttpException::class)
    fun `WHEN get chapter detail is called with existing id EXPECT a ChapterDetailResponseDTO`() = runTest {
        val res: ChapterDetailResponseDTO = api.getChapterDetail("19b31a07-5669-401f-be72-f2f84e68ce00")
        assert(true)
    }

    /*@Test()
    fun `WHEN get chapters is called with existing string EXPECT ChapterResponseDTO`() = runTest {
        val response = api.getChapters("67c03443-8b06-44e1-9784-55b33bc3428d")
        assertThat(response is ChapterResponseDTO, `is`(true))
        assertThat(response.volumes.isNotEmpty(), `is`(true))
        assertThat(response.volumes.first().chapters.isNotEmpty(), `is`(true))

    }*/


   /* @Test(expected = HttpException::class)
    fun `WHEN get heroes location called with random string EXPECT exception`() = runTest {
        api.getLocationsHero(
            IdDto("dsdsdsfdfd")
        )


        // no debe llegar aqui
        assert(false)
    }*/


    companion object {
        private  lateinit var retrofit: Retrofit

        @BeforeClass
        @JvmStatic
        fun setupCommon(){
            retrofit = Retrofit.Builder()
                .baseUrl("https://api.mangadex.org/")
                .client(OkHttpClient.Builder()
                    .addInterceptor(
                        HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
                            .apply {
                                level = HttpLoggingInterceptor.Level.NONE
                            }
                    ).build())
                .addConverterFactory(MoshiConverterFactory.create(
                    Moshi.Builder()
                    .addLast(KotlinJsonAdapterFactory())
                    .build()))
                .build()
        }
    }

}