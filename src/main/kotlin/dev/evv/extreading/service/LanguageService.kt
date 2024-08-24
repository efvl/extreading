package dev.evv.extreading.service

import dev.evv.extreading.dto.LanguageDto
import java.util.UUID

interface LanguageService {

    fun save(languageDto: LanguageDto): LanguageDto

    fun getById(id: UUID): LanguageDto

}