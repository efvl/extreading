package dev.evv.extreading.service

import dev.evv.extreading.dto.DictionaryDto
import dev.evv.extreading.dto.DictionarySearchRequest
import dev.evv.extreading.exception.DictionaryNotFoundException
import dev.evv.extreading.mapper.DictionaryMapper
import dev.evv.extreading.model.DictionaryEntity
import dev.evv.extreading.repository.DictionaryRepository
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class DictionaryServiceImpl (
    private val dictionaryRepository: DictionaryRepository,
    private val dictionaryMapper: DictionaryMapper
) : DictionaryService {

    override fun save(dictionaryDto: DictionaryDto): DictionaryDto {
        val dictionaryEntity: DictionaryEntity = dictionaryRepository.save(dictionaryMapper.toEntity(dictionaryDto))
        return dictionaryMapper.toDto(dictionaryEntity)
    }

    override fun getById(id: UUID): DictionaryDto {
        val dictionaryEntity: DictionaryEntity = dictionaryRepository.findById(id).orElseThrow{ DictionaryNotFoundException(id) }
        return dictionaryMapper.toDto(dictionaryEntity)
    }

    override fun search(searchRequest: DictionarySearchRequest): List<DictionaryDto> {
        return dictionaryRepository.findAll().stream()
            .map(dictionaryMapper::toDto)
            .collect(Collectors.toList())
    }

    override fun deleteDictionaryById(id: UUID) {
        dictionaryRepository.deleteById(id)
    }

    override fun updateDictionary(dictionaryDto: DictionaryDto): DictionaryDto {
        var updated = dictionaryMapper.toDto(dictionaryRepository.save(dictionaryMapper.toEntity(dictionaryDto)))
        return updated
    }
}