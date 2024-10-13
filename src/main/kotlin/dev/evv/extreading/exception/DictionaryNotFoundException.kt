package dev.evv.extreading.exception

import java.util.*

class DictionaryNotFoundException(id: UUID) : RuntimeException() {
    override val message: String = "Dictionary with id: $id was Not Found"
}