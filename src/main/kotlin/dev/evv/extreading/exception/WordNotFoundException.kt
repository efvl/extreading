package dev.evv.extreading.exception

import java.util.*

class WordNotFoundException(id: UUID) : RuntimeException() {
    override val message: String = "Word with id: $id was Not Found"
}