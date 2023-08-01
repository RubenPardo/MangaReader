package com.example.proyectofinalopentech.data.model

class MangaResponseTestDTOBuilder {
    val id = "test-id"
    var title = "Titulo"
    var state = "published"
    var status = "ongoing"
    var tags = listOf<String>()
    var description = ""
    var numElements: Int = 1
    var fileName:String? = "fileNameURL"

    fun withTitle(title: String): MangaResponseTestDTOBuilder {
        this.title = title
        return this
    }
    

    fun withDescription(description: String): MangaResponseTestDTOBuilder {
        this.description = description
        return this
    }

    fun withNumElements(numElements: Int): MangaResponseTestDTOBuilder {
        this.numElements = numElements

        return this
    }
    fun withFileName(fileName: String?): MangaResponseTestDTOBuilder {
        this.fileName = fileName
        return this
    }

    fun build(): MangaResponseDTO {
        val list = mutableListOf<Data>()

        for(i in 0 until numElements) {
            list.add(DataTestBuilder()
                .withId(i.toString())
                .withTitle(title)
                .withDescription(description)
                .withFileName(fileName)
                .build(i))
        }
        return MangaResponseDTO(
            data = list
        )
    }


}