package dev.evv.extreading.service

import dev.evv.extreading.dto.BookDto
import dev.evv.extreading.dto.BookSearchRequest
import java.util.*

interface BookService {

    fun save(bookDto: BookDto): BookDto

    fun getById(id: UUID): BookDto

    fun search(searchRequest: BookSearchRequest): List<BookDto>

    fun deleteBookById(id: UUID)

    fun updateBook(bookDto: BookDto): BookDto

}