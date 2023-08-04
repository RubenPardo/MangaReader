package com.example.proyectofinalopentech.data.model

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type

data class ChapterResponseDTO (

    @SerializedName("result"  ) var result  : String?  = null,
    @SerializedName("volumes" ) var volumes : Map<String, Volume> = emptyMap()

)

data class Volume (
    @SerializedName("volume"  ) var name  : String?  = null,
    @SerializedName("count"  ) var count  : String?  = null,
    @SerializedName("chapters" ) var chapters : Map<String,Chapter> = emptyMap()
)


data class Chapter (
    @SerializedName("chapter"  ) var name  : String?  = null,
    @SerializedName("id"  ) var id  : String?  = null,
)

class VolumeAdapterFactory : JsonAdapter.Factory {
    override fun create(type: Type, annotations: Set<Annotation>, moshi: Moshi): JsonAdapter<*>? {
        if (Types.getRawType(type) == ChapterResponseDTO::class.java) {
            return VolumesResponseAdapter(moshi).nullSafe()
        }
        return null
    }
}

class VolumesResponseAdapter(moshi: Moshi) : JsonAdapter<ChapterResponseDTO>() {
    private val adapter: JsonAdapter<ChapterResponseDTO> = moshi.adapter(ChapterResponseDTO::class.java)

    override fun fromJson(reader: JsonReader): ChapterResponseDTO? {
        val jsonObject = reader.readJsonValue() as Map<String, Any>
        val volumesObject = jsonObject["volumes"] as? Map<String, Any>
        val volumesMap = mutableMapOf<String, Volume>()

        volumesObject?.forEach { (key, value) ->
            val volumeJson = value as? Map<String, Any>
            //val volume = moshi.adapter(Volume::class.java).fromJsonValue(volumeJson)
            val volume = Volume(
                name = volumeJson?.get("volume") as String ?: "",
                (volumeJson["count"] as Double).toString() ?: "0",
                volumeJson["chapters"] as? Map<String, Chapter> ?: emptyMap(),
            )
            volumesMap[key] = volume ?: Volume("", "0", emptyMap())
        }

        return ChapterResponseDTO(
            jsonObject["result"] as? String ?: "",
            volumesMap
        )
    }

    override fun toJson(writer: JsonWriter, value: ChapterResponseDTO?) {
        adapter.toJson(writer, value)
    }
}