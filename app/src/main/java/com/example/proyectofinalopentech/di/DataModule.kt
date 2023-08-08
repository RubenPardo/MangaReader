package com.example.proyectofinalopentech.di

import android.content.Context
import androidx.room.Room
import com.example.proyectofinalopentech.data.api.MangaDexApi
import com.example.proyectofinalopentech.data.local.implementations.LocalDataSourceRoomImpl
import com.example.proyectofinalopentech.data.local.interfaces.LocalDataSource
import com.example.proyectofinalopentech.data.local.room.MangaDatabase
import com.example.proyectofinalopentech.data.local.room.MangaFavDao
import com.example.proyectofinalopentech.data.local.room.MangaPagesFavDao
import com.example.proyectofinalopentech.data.model.ChapterResponseDTOAdapterFactory
import com.example.proyectofinalopentech.data.remote.implementation.RemoteDataSourceRetrofit
import com.example.proyectofinalopentech.data.remote.interfaces.RemoteDataSource
import com.example.proyectofinalopentech.data.repositories.MangaRepositoryImpl
import com.example.proyectofinalopentech.domain.repositoryInterfaces.MangaRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val dataModule = module{

    single<RemoteDataSource> { RemoteDataSourceRetrofit(get()) }
    single <MangaRepository>{ MangaRepositoryImpl(get(),get())  }
    single <LocalDataSource>{ LocalDataSourceRoomImpl(get(),get())  }

    single<MangaDexApi>{
        getMangaDexApi(get())
    }

    single {
        getDatabase(get())
    }

    single {
        providesMangaFavDao(get())
    }

    single {
        providesMangaPageFavDao(get())
    }


    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()
    }

    single<Retrofit>{
        Retrofit.Builder()
            .baseUrl("https://api.mangadex.org/")
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .build()
    }

    single<Moshi>{
        Moshi.Builder()
            .add(ChapterResponseDTOAdapterFactory())
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }


}

private fun getMangaDexApi(retrofit: Retrofit) = retrofit.create(MangaDexApi::class.java)

private fun getDatabase(context: Context) : MangaDatabase =
    Room.databaseBuilder(
        context,
        MangaDatabase::class.java, "manga-fav-db"
    ).build()

private fun providesMangaFavDao(db: MangaDatabase) : MangaFavDao =
    db.mangaFavDao()

private fun providesMangaPageFavDao(db: MangaDatabase) : MangaPagesFavDao =
    db.mangaPageFavDao()