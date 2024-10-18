package dev.evv.extreading.exception

import java.util.*

class WordDuplicateException(nums:String, txt:String, bookId: UUID?) : RuntimeException() {
    override val message: String = "$nums Word $txt for book: $bookId already exists"
}
