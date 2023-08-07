package com.example.proyectofinalopentech.data.model

import com.google.gson.annotations.SerializedName

data class ChapterDetailResponseDTO(
    @SerializedName("result"  ) var result  : String?  = null,
    @SerializedName("baseUrl" ) var baseUrl : String?  = null,
    @SerializedName("chapter" ) var chapter : ChapterDetailDTO? = ChapterDetailDTO()
)

data class ChapterDetailDTO(
    @SerializedName("hash"      ) var hash      : String?           = null,
    @SerializedName("data"      ) var data      : List<String> = emptyList(),
    @SerializedName("dataSaver" ) var dataSaver : List<String> = emptyList()
)
