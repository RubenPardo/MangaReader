package com.example.proyectofinalopentech.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.proyectofinalopentech.data.model.mappers.toDomain
import com.example.proyectofinalopentech.data.remote.MangaPagingSource
import com.example.proyectofinalopentech.data.remote.NETWORK_PAGE_SIZE
import com.example.proyectofinalopentech.data.remote.interfaces.RemoteDataSource
import com.example.proyectofinalopentech.domain.model.Chapter
import com.example.proyectofinalopentech.domain.model.Manga
import com.example.proyectofinalopentech.domain.model.Response
import com.example.proyectofinalopentech.domain.repositoryInterfaces.MangaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MangaRepositoryImpl (private val remoteDataSource: RemoteDataSource) : MangaRepository {
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


}