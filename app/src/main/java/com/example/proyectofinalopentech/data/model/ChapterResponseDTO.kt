package com.example.proyectofinalopentech.data.model

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type

data class ChapterResponseDTO(

    @SerializedName("result"  ) var result: String?  = null,
    @SerializedName("volumes" ) var volumes: List<VolumeDTO> = emptyList()

)

data class VolumeDTO (
    @SerializedName("volume"  ) var name  : String?  = null,
    @SerializedName("count"  ) var count  : String?  = null,
    @SerializedName("chapters" ) var chapters : List<ChapterDTO> = emptyList()
)


data class ChapterDTO (
    @SerializedName("chapter"  ) var name  : String?  = null,
    @SerializedName("id"  ) var id  : String?  = null,
)

class ChapterResponseDTOAdapterFactory : JsonAdapter.Factory {
    override fun create(type: Type, annotations: Set<Annotation>, moshi: Moshi): JsonAdapter<*>? {
        if (Types.getRawType(type) == ChapterResponseDTO::class.java) {
            return ChapterResponseDTOAdapter(moshi).nullSafe()
        }
        return null
    }
}

class ChapterResponseDTOAdapter(moshi: Moshi) : JsonAdapter<ChapterResponseDTO>() {
    private val adapter: JsonAdapter<ChapterResponseDTO> = moshi.adapter(ChapterResponseDTO::class.java)

    override fun fromJson(reader: JsonReader): ChapterResponseDTO {
        val jsonObject = reader.readJsonValue() as Map<String, Any>
        val volumesObject = jsonObject["volumes"] as? Map<String, Any>
        var volumes: List<VolumeDTO>? = listOf<VolumeDTO>()

        volumes = volumesObject?.map { (key, value) ->
            val volumeJson = value as? Map<String, Any>
            //val volume = moshi.adapter(Volume::class.java).fromJsonValue(volumeJson)
            VolumeDTO(
                name = volumeJson?.get("volume") as String,
                (volumeJson["count"] as Double).toString(),
                chaptersFromJson(volumeJson["chapters"] as? Map<String, Any> ),
            )
        }?.toList()

        return ChapterResponseDTO(
            jsonObject["result"] as? String ?: "",
            volumes ?: emptyList()
        )
    }

    private fun chaptersFromJson(chaptersJson: Map<String, Any>?): List<ChapterDTO> {
        return  chaptersJson?.map { (name, value) ->
            val chapterJson = value as? Map<String, Any>
            ChapterDTO(
                name = name,
                (chapterJson?.get("id") as String).toString(),
            )
        }?.toList() ?: emptyList()
    }

    override fun toJson(writer: JsonWriter, value: ChapterResponseDTO?) {
        adapter.toJson(writer, value)
    }
}