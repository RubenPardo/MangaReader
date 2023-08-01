package com.example.proyectofinalopentech.di

import com.example.proyectofinalopentech.data.api.MangaDexApi
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
    single <MangaRepository>{ MangaRepositoryImpl(get())  }
    /*single <DragonBallRepository>{ DragonBallRepositoryImpl(get(), get())  }

    single <SharedPreferencesService>{ SharedPreferencesServiceImpl()  }
    single <AuthService>{ AuthServiceImpl()  }
    single <RemoteDataSource>{ RemoteDataSourceRetrofitImpl(get())  }
    single <LocalDataSource>{ LocalDataSourceRoomImpl(get())  }*/

    single<MangaDexApi>{
        getMangaDexApi(get())
    }

   /* single {
        getDatabase(get())
    }

    single {
        providesHeroDao(get())
    }*/


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
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }


}

private fun getMangaDexApi(retrofit: Retrofit) = retrofit.create(MangaDexApi::class.java)

/*private fun getDatabase(context: Context) : HeroDatabase =
    Room.databaseBuilder(
        context,
        HeroDatabase::class.java, "superhero-db"
    ).build()

private fun providesHeroDao(db: HeroDatabase) : HeroDao =
    db.superHeroDao()*/