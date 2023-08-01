package com.example.proyectofinalopentech.data.model

import com.google.gson.annotations.SerializedName

data class MangaResponseDTO (

    @SerializedName("result"   ) var result   : String? = null,
    @SerializedName("response" ) var response : String? = null,
    @SerializedName("data"     ) var data     : List<Data> = listOf(),
    @SerializedName("limit"    ) var limit    : Int?            = null,
    @SerializedName("offset"   ) var offset   : Int?            = null,
    @SerializedName("total"    ) var total    : Int?            = null,
)


data class Title (

    @SerializedName("en" ) var en : String? = null

)

data class Attributes (

    @SerializedName("title"                          ) var title                          : Title?               = Title(),
    @SerializedName("altTitles"                      ) var altTitles                      : List<AltTitles> = listOf(),
    @SerializedName("description"                    ) var description                    : Description?         = Description(),
    @SerializedName("isLocked"                       ) var isLocked                       : Boolean?             = null,
    @SerializedName("links"                          ) var links                          : Links?               = Links(),
    @SerializedName("originalLanguage"               ) var originalLanguage               : String?              = null,
    @SerializedName("lastVolume"                     ) var lastVolume                     : String?              = null,
    @SerializedName("lastChapter"                    ) var lastChapter                    : String?              = null,
    @SerializedName("publicationDemographic"         ) var publicationDemographic         : String?              = null,
    @SerializedName("status"                         ) var status                         : String?              = null,
    @SerializedName("year"                           ) var year                           : Int?                 = null,
    @SerializedName("contentRating"                  ) var contentRating                  : String?              = null,
    @SerializedName("tags"                           ) var tags                           : List<Tags>      = listOf(),
    @SerializedName("state"                          ) var state                          : String?              = null,
    @SerializedName("chapterNumbersResetOnNewVolume" ) var chapterNumbersResetOnNewVolume : Boolean?             = null,
    @SerializedName("createdAt"                      ) var createdAt                      : String?              = null,
    @SerializedName("updatedAt"                      ) var updatedAt                      : String?              = null,
    @SerializedName("version"                        ) var version                        : Int?                 = null,
    @SerializedName("availableTranslatedLanguages"   ) var availableTranslatedLanguages   : List<String>    = listOf(),
    @SerializedName("latestUploadedChapter"          ) var latestUploadedChapter          : String?              = null

)

data class Relationships (

    @SerializedName("id"   ) var id   : String? = null,
    @SerializedName("type" ) var type : String? = null,
    @SerializedName("attributes" ) var attributes: RelationshipAttributes = RelationshipAttributes(),


    )

data class Data (

    @SerializedName("id"            ) var id            : String?                  = null,
    @SerializedName("type"          ) var type          : String?                  = null,
    @SerializedName("attributes"    ) var attributes    : Attributes?              = Attributes(),
    @SerializedName("relationships" ) var relationships : List<Relationships> = listOf()

)

data class Tags (

    @SerializedName("id"            ) var id            : String?           = null,
    @SerializedName("type"          ) var type          : String?           = null,
    @SerializedName("attributes"    ) var attributes    : TagAttributes?       = TagAttributes(),
    @SerializedName("relationships" ) var relationships : List<String> = listOf()

)

data class RelationshipAttributes (

    @SerializedName("description") var name: String? = null,
    @SerializedName("volume") var group: String? = null,
    @SerializedName("fileName") var fileName: String? = null,
    @SerializedName("locale") var locale: String? = null,
    @SerializedName("createdAt") var createdAt: String? = null,
    @SerializedName("updatedAt") var updatedAt: String? = null,
)


data class TagAttributes (

    @SerializedName("name") var name: Name? = Name(),
    @SerializedName("group") var group: String? = null,

    )

data class Name (
    @SerializedName("en" ) var en : String? = null
)

data class Description (
    @SerializedName("en" ) var en : String? = null
)

data class AltTitles (

    @SerializedName("ja" ) var ja : String? = null

)

data class Links (

    @SerializedName("al"  ) var al  : String? = null,
    @SerializedName("ap"  ) var ap  : String? = null,
    @SerializedName("bw"  ) var bw  : String? = null,
    @SerializedName("kt"  ) var kt  : String? = null,
    @SerializedName("mu"  ) var mu  : String? = null,
    @SerializedName("amz" ) var amz : String? = null,
    @SerializedName("ebj" ) var ebj : String? = null,
    @SerializedName("mal" ) var mal : String? = null

)