package com.example.proyectofinalopentech.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.proyectofinalopentech.data.local.interfaces.LocalDataSource
import com.example.proyectofinalopentech.data.model.mappers.toDomain
import com.example.proyectofinalopentech.data.model.mappers.toLocal
import com.example.proyectofinalopentech.data.remote.MangaPagingSource
import com.example.proyectofinalopentech.data.remote.NETWORK_PAGE_SIZE
import com.example.proyectofinalopentech.data.remote.interfaces.RemoteDataSource
import com.example.proyectofinalopentech.domain.model.Chapter
import com.example.proyectofinalopentech.domain.model.ChapterDetail
import com.example.proyectofinalopentech.domain.model.Manga
import com.example.proyectofinalopentech.domain.model.Response
import com.example.proyectofinalopentech.domain.repositoryInterfaces.MangaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MangaRepositoryImpl (
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : MangaRepository {
    override fun getMangasByName(mangaName: String): Flow<PagingData<Manga>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = {
                MangaPagingSource(mangaName,remoteDataSource)
            }
        ).flow.map { it.map { dto -> dto.toDomain() } }
    }

    override suspend fun getChaptersByMangaId(mangaId: String): Response<List<Chapter>> {
        return try {
            Response.Success(remoteDataSource.getChaptersByMangaId(mangaId).toDomain())
        }catch (e: Exception){
            Response.Error(e.message)
        }
    }

    override suspend fun getMangaInfo(mangaId: String): Response<Manga> {
        return try {
            val mangaDTO = remoteDataSource.getMangaById(mangaId).data
            if(mangaDTO!=null){
                Response.Success(mangaDTO.toDomain())
            }else{
                Response.Error("Unable to parse")
            }

        }catch (e: Exception){
            Response.Error(e.message)
        }
    }

    override suspend fun getFavMangas(): Response<List<Manga>> {
        return try {
            val mangaLocal = localDataSource.getFavMangas()
            Response.Success(mangaLocal.map { it.toDomain() })

        }catch (e: Exception){
            Response.Error(e.message)
        }
    }

    override suspend fun saveFavManga(manga: Manga): Response<Boolean> {
        return try {
            val res = localDataSource.insertFavManga(manga.toLocal())
            if(res>0L){
                Response.Success(true)
            }else{
                Response.Error("")
            }


        }catch (e: Exception){
            Response.Error(e.message)
        }
    }

    override suspend fun removeFavManga(manga: Manga): Response<Boolean> {
        return try {
            localDataSource.removeFavManga(manga.toLocal())
            Response.Success(true)

        }catch (e: Exception){
            Response.Error(e.message)
        }
    }

    override suspend fun isFavManga(mangaId: String): Response<Boolean> {
        return try {
            Response.Success(localDataSource.isFavManga(mangaId))
        }catch (e: Exception){
            Response.Error(e.message)
        }
    }

    override suspend fun getChapterDetail(chapterId: String): Response<ChapterDetail> {
        return try {
            val res = remoteDataSource.getChapterDetail(chapterId)
            println(res.result)
            Response.Success(res.toDomain())
        }catch (e: Exception){
            Response.Error(e.message)
        }
    }


}