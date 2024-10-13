package dev.evv.extreading.dto

data class BookSearchRequest(
    var ids: List<Long?>? = null,
    var languageId: String? = null,
    var fullName: String? = null,
)

