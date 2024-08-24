package dev.evv.extreading.dto

import java.util.UUID

data class LanguageDto(
    var id: UUID? = null,
    var shortName: String = "",
    var fullName: String = "",
)
