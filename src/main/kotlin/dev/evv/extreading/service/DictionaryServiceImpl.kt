package dev.evv.extreading.service

import com.querydsl.core.BooleanBuilder
import com.querydsl.jpa.impl.JPAQueryFactory
import dev.evv.extreading.dto.DictionaryDto
import dev.evv.extreading.dto.DictionarySearchRequest
import dev.evv.extreading.exception.DictionaryNotFoundException
import dev.evv.extreading.mapper.DictionaryMapper
import dev.evv.extreading.model.DictionaryEntity
import dev.evv.extreading.model.QDictionaryEntity
import dev.evv.extreading.repository.DictionaryRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class DictionaryServiceImpl (
    private val dictionaryRepository: DictionaryRepository,
    private val dictionaryMapper: DictionaryMapper,
    private val queryFactory: JPAQueryFactory
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
        val qDictEntity = QDictionaryEntity.dictionaryEntity
        val query = queryFactory.selectFrom(qDictEntity)
        val whereCause: BooleanBuilder = BooleanBuilder()
        if (searchRequest.languageId != null){
            whereCause.and(qDictEntity.language.id.eq(searchRequest.languageId))
        }
        query.from(qDictEntity).where(whereCause)
        val dictList = query.fetch()
        return dictList.stream()
            .map(dictionaryMapper::toDto)
            .toList()
    }

    override fun deleteDictionaryById(id: UUID) {
        dictionaryRepository.deleteById(id)
    }

    override fun updateDictionary(dictionaryDto: DictionaryDto): DictionaryDto {
        var updated = dictionaryMapper.toDto(dictionaryRepository.save(dictionaryMapper.toEntity(dictionaryDto)))
        return updated
    }
}