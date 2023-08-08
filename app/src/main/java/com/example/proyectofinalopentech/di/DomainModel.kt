package com.example.proyectofinalopentech.di

import com.example.proyectofinalopentech.domain.usecases.GetChapterDetailUseCase
import com.example.proyectofinalopentech.domain.usecases.GetFavMangasUseCase
import com.example.proyectofinalopentech.domain.usecases.GetMangaChaptersByIdUseCase
import com.example.proyectofinalopentech.domain.usecases.GetMangaInfoUseCase
import com.example.proyectofinalopentech.domain.usecases.GetMangasByNameUseCase
import com.example.proyectofinalopentech.domain.usecases.SetMangaFavUseCase
import com.example.proyectofinalopentech.domain.usecases.SetMangaPageFavUseCase
import org.koin.dsl.module

val domainModel = module{

   factory {GetMangasByNameUseCase(get())}
   factory {GetMangaChaptersByIdUseCase(get())}
   factory {GetMangaInfoUseCase(get())}
   factory {SetMangaFavUseCase(get())}
   factory { SetMangaPageFavUseCase(get()) }
   factory {GetFavMangasUseCase(get())}
   factory {GetChapterDetailUseCase(get())}

}