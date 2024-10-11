package dev.evv.extreading.dto

import java.util.*

data class BookDto(
    var id: UUID? = null,
    var language: LanguageDto? = null,
    var title: String = "",
    var authors: String = "",
    var year: Int = 0,
    var info: String = "",
)
