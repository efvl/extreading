package dev.evv.extreading.dto

import java.util.*

data class DictionaryStats(
    var dictionary: DictionaryDto? = null,
    var countInBook: Int? = 0,
    var bookId: UUID? = null,
)
