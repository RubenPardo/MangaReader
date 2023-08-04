package com.example.proyectofinalopentech.di


import com.example.proyectofinalopentech.presentation.mangalist.viewmodels.MangaListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module{
    viewModel{ MangaListViewModel(get(),get()) }
    viewModelOf(::MangaListViewModel)
    /*viewModel{ HeroListViewModel(get()) }
    viewModelOf(::HeroListViewModel)
    viewModel{ HeroDetailsViewModel(get(),get(), get(),get(),get()) }
    viewModelOf(::HeroDetailsViewModel)*/
}