package com.example.proyectofinalopentech.domain.usecases

import androidx.paging.PagingData
import com.example.proyectofinalopentech.domain.model.Manga
import com.example.proyectofinalopentech.domain.repositoryInterfaces.MangaRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class GetMangasByNameUseCase(
    private val repository: MangaRepository
) {

    fun invoke(mangaName: String):
            Flow<PagingData<Manga>> = repository.getMangasByName(mangaName)

}