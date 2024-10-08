package dev.evv.extreading.service

import dev.evv.extreading.dto.LanguageDto
import dev.evv.extreading.dto.LanguageSearchRequest
import java.util.*

interface LanguageService {

    fun save(languageDto: LanguageDto): LanguageDto

    fun getById(id: UUID): LanguageDto

    fun search(searchRequest: LanguageSearchRequest): List<LanguageDto>

}