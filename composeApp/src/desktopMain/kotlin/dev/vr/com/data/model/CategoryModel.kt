package dev.vr.com.data.model

enum class CategoryModel (
    val key: String
) {
    ARENA("Arena"),
    ZONE("Zone"),
    HOLIDAYS("Holidays"),
    OUR_PARK("У нас в парке"),
    OUR_GAMES("О наших играх"),

    /*companion object {
        fun fromKey(key: String): CategoryModel =
            entries.find { it.key == key }
                ?: error("Unknown category: $key")
    }*/
}

