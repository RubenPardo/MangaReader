package com.example.proyectofinalopentech.di


import com.example.proyectofinalopentech.presentation.viewmodels.MangaDetailsViewModel
import com.example.proyectofinalopentech.presentation.viewmodels.MangaListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module{
    viewModel{ MangaListViewModel(get()) }
    viewModelOf(::MangaListViewModel)
    viewModel{ MangaDetailsViewModel(get(),get(),get()) }
    viewModelOf(::MangaDetailsViewModel)
    //viewModel{ HeroDetailsViewModel(get(),get(), get(),get(),get()) }
    //viewModelOf(::HeroDetailsViewModel)*/
}