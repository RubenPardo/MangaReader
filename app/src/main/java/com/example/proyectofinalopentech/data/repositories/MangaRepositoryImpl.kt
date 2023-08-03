package com.example.proyectofinalopentech.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.proyectofinalopentech.data.model.mappers.toDomain
import com.example.proyectofinalopentech.data.remote.MangaPagingSource
import com.example.proyectofinalopentech.data.remote.NETWORK_PAGE_SIZE
import com.example.proyectofinalopentech.data.remote.interfaces.RemoteDataSource
import com.example.proyectofinalopentech.domain.model.Manga
import com.example.proyectofinalopentech.domain.repositoryInterfaces.MangaRepository
import kotlinx.coroutines.CoroutineScope
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

    /*override suspend fun getMangas(offset: Int, limit: Int): LiveData<PagingData<Manga>> {
        return try{
            val mangaResponseDTO = remoteDataSource.getMangaList(offset,limit)
            Response.Success(mangaResponseDTO.toDomain())
        }catch (e:Exception){ Response.Error(e.message) }
    }*/
}