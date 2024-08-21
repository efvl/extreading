package dev.evv.extreading.service

import dev.evv.extreading.dto.LanguageDto

interface LanguageService {

    fun save(languageDto: LanguageDto): LanguageDto

}