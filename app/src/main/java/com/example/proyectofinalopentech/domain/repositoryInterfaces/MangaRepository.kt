package com.example.proyectofinalopentech.domain.repositoryInterfaces

import androidx.paging.PagingData
import com.example.proyectofinalopentech.domain.model.Manga
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface MangaRepository {

    fun getMangas(scope:CoroutineScope): Flow<PagingData<Manga>>

}