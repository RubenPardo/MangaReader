package com.example.proyectofinalopentech.di

import com.example.proyectofinalopentech.domain.usecases.GetMangasUseCase
import org.koin.dsl.module

val domainModel = module{

   factory {GetMangasUseCase(get())}
   /* factory {LoginUseCase(get())}
    factory {GetHeroDetailByIdUseCase(get())}
    factory {SetHeroFavByIdUseCase(get())}
    factory {GetHeroLasLocationUseCase(get())}
    factory {GetDistanceFroHeroUseCase()}*/


}