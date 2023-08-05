package com.example.proyectofinalopentech.di

import com.example.proyectofinalopentech.domain.usecases.GetMangaChaptersByIdUseCase
import com.example.proyectofinalopentech.domain.usecases.GetMangasByNameUseCase
import org.koin.dsl.module

val domainModel = module{

   factory {GetMangasByNameUseCase(get())}
   factory {GetMangaChaptersByIdUseCase(get())}

}