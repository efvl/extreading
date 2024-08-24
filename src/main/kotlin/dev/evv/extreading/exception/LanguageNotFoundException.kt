package dev.evv.extreading.exception

import java.util.UUID

class LanguageNotFoundException(id: UUID) : RuntimeException() {
    override val message: String = "Language with id: $id was Not Found"
}
