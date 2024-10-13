package dev.evv.extreading.service

import dev.evv.extreading.dto.DictionaryDto
import dev.evv.extreading.dto.DictionarySearchRequest
import java.util.*

interface DictionaryService {

    fun save(dictionaryDto: DictionaryDto): DictionaryDto

    fun getById(id: UUID): DictionaryDto

    fun search(searchRequest: DictionarySearchRequest): List<DictionaryDto>

    fun deleteDictionaryById(id: UUID)

    fun updateDictionary(dictionaryDto: DictionaryDto): DictionaryDto

}