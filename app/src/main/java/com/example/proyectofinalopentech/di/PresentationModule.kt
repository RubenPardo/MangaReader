package com.example.proyectofinalopentech.di


import com.example.proyectofinalopentech.presentation.viewmodels.ChapterReaderViewModel
import com.example.proyectofinalopentech.presentation.viewmodels.MangaDetailsViewModel
import com.example.proyectofinalopentech.presentation.viewmodels.MangaListViewModel
import com.example.proyectofinalopentech.presentation.viewmodels.MyMangasViewModel
import com.example.proyectofinalopentech.presentation.viewmodels.MyPagesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module{
    viewModel{ MangaListViewModel(get()) }
    viewModelOf(::MangaListViewModel)
    viewModel{ MangaDetailsViewModel(get(),get(),get()) }
    viewModelOf(::MangaDetailsViewModel)
    viewModel{ MyMangasViewModel(get()) }
    viewModelOf(::MyMangasViewModel)
    viewModel{ ChapterReaderViewModel(get(),get()) }
    viewModelOf(::ChapterReaderViewModel)
    viewModel{ MyPagesViewModel(get()) }
    viewModelOf(::MyPagesViewModel)
}