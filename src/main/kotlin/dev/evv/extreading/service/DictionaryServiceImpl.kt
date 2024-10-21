package dev.evv.extreading.service

import com.querydsl.core.BooleanBuilder
import com.querydsl.jpa.impl.JPAQueryFactory
import dev.evv.extreading.dto.DictionaryDto
import dev.evv.extreading.dto.DictionarySearchRequest
import dev.evv.extreading.dto.DictionaryStats
import dev.evv.extreading.exception.DictionaryDuplicateException
import dev.evv.extreading.exception.DictionaryNotFoundException
import dev.evv.extreading.mapper.DictionaryMapper
import dev.evv.extreading.model.DictionaryEntity
import dev.evv.extreading.model.QDictionaryEntity
import dev.evv.extreading.repository.DictionaryRepository
import dev.evv.extreading.repository.WordRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class DictionaryServiceImpl (
    private val dictionaryRepository: DictionaryRepository,
    private val wordRepository: WordRepository,
    private val dictionaryMapper: DictionaryMapper,
    private val queryFactory: JPAQueryFactory
) : DictionaryService {

    override fun save(dictionaryDto: DictionaryDto): DictionaryDto {
        val listDict = search(DictionarySearchRequest(
            languageId = dictionaryDto.language.id,
            txtContent = dictionaryDto.txtContent)
        )
        if (listDict.isNotEmpty()) {
            throw DictionaryDuplicateException(dictionaryDto.txtContent, dictionaryDto.language.fullName)
        }
        val dictionaryEntity: DictionaryEntity = dictionaryRepository.save(dictionaryMapper.toEntity(dictionaryDto))
        return dictionaryMapper.toDto(dictionaryEntity)
    }

    override fun getById(id: UUID): DictionaryDto {
        val dictionaryEntity: DictionaryEntity = dictionaryRepository.findById(id).orElseThrow{ DictionaryNotFoundException(id) }
        return dictionaryMapper.toDto(dictionaryEntity)
    }

    override fun searchAndStats(searchRequest: DictionarySearchRequest): DictionaryStats {
        val result = search(DictionarySearchRequest(
            languageId = searchRequest.languageId,
            txtContent = searchRequest.txtContent
        ))
        val countInBook = wordRepository.countWordsInBook(
            bookId = searchRequest.bookId,
            txtContent = searchRequest.txtContent
        )
        if (result.isEmpty()) {
            return DictionaryStats(countInBook = countInBook, bookId = searchRequest.bookId)
        }
        return DictionaryStats(dictionary = result[0], countInBook = countInBook, bookId = searchRequest.bookId)
    }

    override fun search(searchRequest: DictionarySearchRequest): List<DictionaryDto> {
        val qDictEntity = QDictionaryEntity.dictionaryEntity
        val query = queryFactory.selectFrom(qDictEntity)
        val whereCause: BooleanBuilder = BooleanBuilder()
        if (searchRequest.languageId != null) {
            whereCause.and(qDictEntity.language.id.eq(searchRequest.languageId))
        }
        if (searchRequest.txtContent != null) {
            whereCause.and(qDictEntity.txtContent.eq(searchRequest.txtContent))
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