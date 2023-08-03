package com.example.proyectofinalopentech.di

import com.example.proyectofinalopentech.domain.usecases.GetMangasByNameUseCase
import org.koin.dsl.module

val domainModel = module{

   factory {GetMangasByNameUseCase(get())}
   /* factory {LoginUseCase(get())}
    factory {GetHeroDetailByIdUseCase(get())}
    factory {SetHeroFavByIdUseCase(get())}
    factory {GetHeroLasLocationUseCase(get())}
    factory {GetDistanceFroHeroUseCase()}*/


}