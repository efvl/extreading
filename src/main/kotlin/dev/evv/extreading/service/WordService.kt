package dev.evv.extreading.service

import dev.evv.extreading.dto.ExrWordDto
import dev.evv.extreading.dto.ExrWordListDto
import dev.evv.extreading.dto.WordSearchRequest
import java.util.*

interface WordService {

    fun save(wordDto: ExrWordDto): ExrWordDto

    fun getById(id: UUID): ExrWordDto

    fun getPageWords(searchRequest: WordSearchRequest): List<ExrWordDto>

    fun createPageWords(wordList: ExrWordListDto): List<ExrWordDto>

    fun search(searchRequest: WordSearchRequest): List<ExrWordDto>

    fun deleteWordById(id: UUID)

    fun updateWord(wordDto: ExrWordDto): ExrWordDto

}