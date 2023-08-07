package com.example.proyectofinalopentech.di

import com.example.proyectofinalopentech.domain.usecases.GetMangaChaptersByIdUseCase
import com.example.proyectofinalopentech.domain.usecases.GetMangaInfoUseCase
import com.example.proyectofinalopentech.domain.usecases.GetMangasByNameUseCase
import com.example.proyectofinalopentech.domain.usecases.SetMangaFavUseCase
import org.koin.dsl.module

val domainModel = module{

   factory {GetMangasByNameUseCase(get())}
   factory {GetMangaChaptersByIdUseCase(get())}
   factory {GetMangaInfoUseCase(get())}
   factory {SetMangaFavUseCase(get())}

}