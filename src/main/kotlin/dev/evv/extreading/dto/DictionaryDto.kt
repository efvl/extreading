package dev.evv.extreading.dto

import dev.evv.extreading.model.LanguageEntity
import java.util.*

data class DictionaryDto(
    var id: UUID? = null,
    var language: LanguageEntity,
    var baseForm: String = "",
    var definition: String = "",
    var txtContent: String = "",
    var grammar: String = "",
)