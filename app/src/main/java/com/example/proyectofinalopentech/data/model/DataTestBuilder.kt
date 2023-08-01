package com.example.proyectofinalopentech.data.model

class DataTestBuilder {
    var title = "Titulo"
    var description = ""
    var fileName:String? = "fileNameURL"
    var id = ""

    fun withTitle(title: String): DataTestBuilder {
        this.title = title
        return this
    }
    fun withId(id: String): DataTestBuilder {
        this.id = id
        return this
    }



    fun withDescription(description: String): DataTestBuilder {
        this.description = description
        return this
    }

    fun withFileName(fileName: String?): DataTestBuilder {
        this.fileName = fileName

        return this
    }

    fun build(index:Int): Data {
        return Data(
            id = id,
            type = "manga",
            attributes = Attributes(
                title = Title(en = "$title $index"),
                altTitles = listOf(AltTitles(ja = "$title $index en japones")),
                description = Description(en = description),
                isLocked = false,
                lastChapter = "13",
                lastVolume = "2",
                state = "published",
                status = "ongoing",
                year = 2013,
                tags = listOf(Tags(id = "2","manga",
                    attributes = TagAttributes(name = Name("Drama"), group = "genre")
                ))
            ),
            relationships = listOf(Relationships(
                id = "34",
                type = "cover_art",
                attributes = RelationshipAttributes(name = "a", fileName = fileName)
            )),
        )
    }
}