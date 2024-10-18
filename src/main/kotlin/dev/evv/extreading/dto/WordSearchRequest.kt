package dev.evv.extreading.dto

import java.util.*

data class WordSearchRequest(
    var ids: List<Long?>? = null,
    var shortName: String? = null,
    var fullName: String? = null,
    var txtContent: String? = null,
    var bookId: UUID? = null,
    var pageNum: Int? = null,
    var lineNum: Int? = null,
    var wordNum: Int? = null,
)

