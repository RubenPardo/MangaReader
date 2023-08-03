package com.example.proyectofinalopentech.data.remote.implementation

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.proyectofinalopentech.data.api.MangaDexApi
import com.example.proyectofinalopentech.data.model.MangaResponseDTO
import com.example.proyectofinalopentech.data.remote.interfaces.RemoteDataSource



class RemoteDataSourceRetrofit(
    private val mangaDexApi: MangaDexApi
) : RemoteDataSource {
    override suspend fun getMangaList(offset: Int, limit: Int): MangaResponseDTO = mangaDexApi.getMangas(offset,limit)
}