package dev.evv.extreading.service

import com.querydsl.core.BooleanBuilder
import com.querydsl.jpa.impl.JPAQueryFactory
import dev.evv.extreading.dto.ExrWordDto
import dev.evv.extreading.dto.WordSearchRequest
import dev.evv.extreading.exception.DictionaryDuplicateException
import dev.evv.extreading.exception.WordDuplicateException
import dev.evv.extreading.exception.WordNotFoundException
import dev.evv.extreading.mapper.ExrWordMapper
import dev.evv.extreading.model.ExrWordEntity
import dev.evv.extreading.model.QExrWordEntity
import dev.evv.extreading.repository.WordRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class WordServiceImpl (
    private val wordRepository: WordRepository,
    private val wordMapper: ExrWordMapper,
    private val queryFactory: JPAQueryFactory
) : WordService {

    override fun save(wordDto: ExrWordDto): ExrWordDto {
        val listWords = search(WordSearchRequest(
            bookId = wordDto.book.id,
            pageNum = wordDto.pageNum,
            lineNum = wordDto.lineNum,
            wordNum = wordDto.wordNum
        ))
        if (listWords.isNotEmpty()) {
            throw WordDuplicateException("page:${wordDto.pageNum} line:${wordDto.lineNum} word:${wordDto.wordNum}",
                wordDto.txtContent, wordDto.book.id)
        }
        val wordEntity: ExrWordEntity = wordRepository.save(wordMapper.toEntity(wordDto))
        return wordMapper.toDto(wordEntity)
    }

    override fun getById(id: UUID): ExrWordDto {
        val wordEntity: ExrWordEntity = wordRepository.findById(id).orElseThrow{ WordNotFoundException(id) }
        return wordMapper.toDto(wordEntity)
    }

    override fun search(searchRequest: WordSearchRequest): List<ExrWordDto> {
        val qWordEntity = QExrWordEntity.exrWordEntity
        val query = queryFactory.selectFrom(qWordEntity)
        val whereCause: BooleanBuilder = BooleanBuilder()
        if (searchRequest.bookId != null) {
            whereCause.and(qWordEntity.book.id.eq(searchRequest.bookId))
        }
        if (searchRequest.txtContent != null) {
            whereCause.and(qWordEntity.txtContent.eq(searchRequest.txtContent))
        }
        if (searchRequest.pageNum != null) {
            whereCause.and(qWordEntity.pageNum.eq(searchRequest.pageNum))
        }
        if (searchRequest.lineNum != null) {
            whereCause.and(qWordEntity.lineNum.eq(searchRequest.lineNum))
        }
        if (searchRequest.wordNum != null) {
            whereCause.and(qWordEntity.wordNum.eq(searchRequest.wordNum))
        }
        query.from(qWordEntity).where(whereCause)
        val wordList = query.fetch()
        return wordList.stream()
            .map(wordMapper::toDto)
            .toList()
    }

    override fun deleteWordById(id: UUID) {
        wordRepository.deleteById(id)
    }

    override fun updateWord(wordDto: ExrWordDto): ExrWordDto {
        var updated = wordMapper.toDto(wordRepository.save(wordMapper.toEntity(wordDto)))
        return updated
    }
}