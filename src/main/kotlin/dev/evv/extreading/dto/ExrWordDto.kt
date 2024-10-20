package dev.evv.extreading.dto

import java.util.*

data class ExrWordDto(
    var id: UUID? = null,
    var book: BookDto? = null,
    var pageNum: Int = 0,
    var lineNum: Int = 0,
    var wordNum: Int = 0,
    var original: String = "",
    var txtContent: String = "",
    var dictionary: DictionaryDto? = null,
)