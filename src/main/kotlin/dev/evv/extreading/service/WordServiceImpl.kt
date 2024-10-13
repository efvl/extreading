package dev.evv.extreading.service

import dev.evv.extreading.dto.ExrWordDto
import dev.evv.extreading.dto.WordSearchRequest
import dev.evv.extreading.exception.WordNotFoundException
import dev.evv.extreading.mapper.ExrWordMapper
import dev.evv.extreading.model.ExrWordEntity
import dev.evv.extreading.repository.WordRepository
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class WordServiceImpl (
    private val wordRepository: WordRepository,
    private val wordMapper: ExrWordMapper
) : WordService {

    override fun save(wordDto: ExrWordDto): ExrWordDto {
        val wordEntity: ExrWordEntity = wordRepository.save(wordMapper.toEntity(wordDto))
        return wordMapper.toDto(wordEntity)
    }

    override fun getById(id: UUID): ExrWordDto {
        val wordEntity: ExrWordEntity = wordRepository.findById(id).orElseThrow{ WordNotFoundException(id) }
        return wordMapper.toDto(wordEntity)
    }

    override fun search(searchRequest: WordSearchRequest): List<ExrWordDto> {
        return wordRepository.findAll().stream()
            .map(wordMapper::toDto)
            .collect(Collectors.toList())
    }

    override fun deleteWordById(id: UUID) {
        wordRepository.deleteById(id)
    }

    override fun updateWord(wordDto: ExrWordDto): ExrWordDto {
        var updated = wordMapper.toDto(wordRepository.save(wordMapper.toEntity(wordDto)))
        return updated
    }
}