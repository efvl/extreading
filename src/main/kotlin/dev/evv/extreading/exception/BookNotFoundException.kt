package dev.evv.extreading.exception

import java.util.*

class BookNotFoundException(id: UUID) : RuntimeException() {
    override val message: String = "Book with id: $id was Not Found"
}
