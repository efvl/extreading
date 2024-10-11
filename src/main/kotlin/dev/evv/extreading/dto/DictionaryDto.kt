package dev.evv.extreading.dto

import dev.evv.extreading.model.LanguageEntity
import jakarta.persistence.Column
import java.util.*

data class DictionaryDto(
    var id: UUID? = null,
    var language: LanguageEntity,
    var baseForm: String = "",
    var definition: String = "",
    var example1: String = "",
    var example2: String = "",
    var info: String = "",
)