package dev.evv.extreading.service

import dev.evv.extreading.dto.LanguageDto
import dev.evv.extreading.dto.LanguageSearchRequest
import dev.evv.extreading.exception.LanguageNotFoundException
import dev.evv.extreading.mapper.LanguageMapper
import dev.evv.extreading.model.LanguageEntity
import dev.evv.extreading.repository.LanguageRepository
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class LanguageServiceImpl(
    private val languageRepository: LanguageRepository,
    private val languageMapper: LanguageMapper
) : LanguageService {

    override fun save(languageDto: LanguageDto): LanguageDto {
        val languageEntity: LanguageEntity = languageRepository.save(languageMapper.toEntity(languageDto))
        return languageMapper.toDto(languageEntity)
    }

    override fun getById(id: UUID): LanguageDto {
        val languageEntity: LanguageEntity = languageRepository.findById(id).orElseThrow{ LanguageNotFoundException(id) }
        return languageMapper.toDto(languageEntity)
    }

    override fun search(searchRequest: LanguageSearchRequest): List<LanguageDto> {
        return languageRepository.findAll().stream()
            .map(languageMapper::toDto)
            .collect(Collectors.toList())
    }

    override fun deleteLanguageById(id: UUID) {
        languageRepository.deleteById(id)
    }

    override fun updateLanguage(languageDto: LanguageDto?): LanguageDto? {
        if (languageDto != null) {
            var updated = languageMapper.toDto(languageRepository.save(languageMapper.toEntity(languageDto)))
            return updated
        }
        return LanguageDto()
    }
}