package dev.evv.extreading.service

import dev.evv.extreading.dto.LanguageDto
import dev.evv.extreading.model.LanguageEntity
import dev.evv.extreading.repository.LanguageRepository
import org.springframework.stereotype.Service

@Service
class LanguageServiceImpl(
    private val languageRepository: LanguageRepository
) : LanguageService {

    override fun save(languageDto: LanguageDto): LanguageDto {
        val languageEntity: LanguageEntity = languageRepository.save(toEntity(languageDto))
        return toDomain(languageEntity)
    }

    private fun toDomain(entity: LanguageEntity): LanguageDto  {
        return LanguageDto(entity.id, entity.shortName, entity.fullName)
    }

    private fun toEntity(dto: LanguageDto): LanguageEntity {
        val entity: LanguageEntity = LanguageEntity(dto.shortName, dto.fullName)
        entity.id = dto.id
        return entity
    }

}