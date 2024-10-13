package dev.evv.extreading.service

import dev.evv.extreading.dto.BookDto
import dev.evv.extreading.dto.BookSearchRequest
import dev.evv.extreading.dto.ExrWordDto
import dev.evv.extreading.dto.WordSearchRequest
import java.util.*

interface WordService {

    fun save(wordDto: ExrWordDto): ExrWordDto

    fun getById(id: UUID): ExrWordDto

    fun search(searchRequest: WordSearchRequest): List<ExrWordDto>

    fun deleteWordById(id: UUID)

    fun updateWord(wordDto: ExrWordDto): ExrWordDto

}