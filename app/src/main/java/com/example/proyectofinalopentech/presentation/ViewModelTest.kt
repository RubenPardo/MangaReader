package com.example.proyectofinalopentech.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinalopentech.data.remote.interfaces.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelTest(
    private val remoteDataSource: RemoteDataSource
): ViewModel() {
    fun test() {
        viewModelScope.launch(Dispatchers.IO){
            val a = remoteDataSource.getMangaList(0,10)
            a.data.forEach {
                println("=================================")
                it.attributes?.let {atr->
                    println("TITULO: ${atr.title?.en}")
                    println("VLM: ${atr.lastVolume}")
                    println("CHPT: ${atr.lastChapter}")
                    println("RATING: ${atr.contentRating}")
                    println("CREATED: ${atr.createdAt}")
                    println("UPDATED AT: ${atr.updatedAt}")
                    println("DESC: ${atr.description}")
                    println("STATE: ${atr.state}")
                    println("STATUS: ${atr.status}")

                    atr.tags.forEach { tag ->
                        println("TAG: $tag")
                    }
                }

                it.relationships.forEach{ rlt ->
                    println("TYPE ${rlt.type}")
                    println("ID ${rlt.id}")
                    println("COVER IMAGE: https://uploads.mangadex.org/covers/${it.id}/${rlt.attributes.fileName}.256.jp")
                }
            }
        }
    }
}