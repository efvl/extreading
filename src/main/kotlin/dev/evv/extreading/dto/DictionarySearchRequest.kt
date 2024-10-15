package dev.evv.extreading.dto

import java.util.*

data class DictionarySearchRequest(
    var ids: List<Long?>? = null,
    var languageId: UUID? = null,
    var fullName: String? = null,
)

