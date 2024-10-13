package dev.evv.extreading.dto

data class DictionarySearchRequest(
    var ids: List<Long?>? = null,
    var shortName: String? = null,
    var fullName: String? = null,
)

