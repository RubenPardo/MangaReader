package com.example.proyectofinalopentech.data.repositories
import com.example.proyectofinalopentech.data.model.mappers.toDomain
import com.example.proyectofinalopentech.data.remote.interfaces.RemoteDataSource
import com.example.proyectofinalopentech.domain.model.Manga
import com.example.proyectofinalopentech.domain.model.Response
import com.example.proyectofinalopentech.domain.repositoryInterfaces.MangaRepository
import java.lang.Exception
class MangaRepositoryImpl (private val remoteDataSource: RemoteDataSource) : MangaRepository {
    override suspend fun getMangas(offset: Int, limit: Int): Response<List<Manga>> {
        return try{
            val mangaResponseDTO = remoteDataSource.getMangaList(offset,limit)
            Response.Success(mangaResponseDTO.toDomain())
        }catch (e:Exception){ Response.Error(e.message) }
    }
}