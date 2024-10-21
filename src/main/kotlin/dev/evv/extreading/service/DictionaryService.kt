package dev.evv.extreading.service

import dev.evv.extreading.dto.DictionaryDto
import dev.evv.extreading.dto.DictionarySearchRequest
import dev.evv.extreading.dto.DictionaryStats
import java.util.*

interface DictionaryService {

    fun save(dictionaryDto: DictionaryDto): DictionaryDto

    fun getById(id: UUID): DictionaryDto

    fun search(searchRequest: DictionarySearchRequest): List<DictionaryDto>

    fun searchAndStats(searchRequest: DictionarySearchRequest): DictionaryStats

    fun deleteDictionaryById(id: UUID)

    fun updateDictionary(dictionaryDto: DictionaryDto): DictionaryDto

}