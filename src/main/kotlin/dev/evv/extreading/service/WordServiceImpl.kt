package dev.evv.extreading.service

import com.querydsl.core.BooleanBuilder
import com.querydsl.jpa.impl.JPAQueryFactory
import dev.evv.extreading.dto.ExrWordDto
import dev.evv.extreading.dto.ExrWordListDto
import dev.evv.extreading.dto.WordSearchRequest
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
        if (isWordExists(wordDto)) {
            throw WordDuplicateException("page:${wordDto.pageNum} line:${wordDto.lineNum} word:${wordDto.wordNum}",
                wordDto.txtContent, wordDto.book?.id)
        }
        val wordEntity: ExrWordEntity = wordRepository.save(wordMapper.toEntity(wordDto))
        return wordMapper.toDto(wordEntity)
    }

    override fun getById(id: UUID): ExrWordDto {
        val wordEntity: ExrWordEntity = wordRepository.findById(id).orElseThrow{ WordNotFoundException(id) }
        return wordMapper.toDto(wordEntity)
    }

    override fun getPageWords(searchRequest: WordSearchRequest): List<ExrWordDto> {
        val listWords = search(WordSearchRequest(
            bookId = searchRequest.bookId,
            pageNum = searchRequest.pageNum,
        ))
        return listWords
    }

    override fun getLast5LinesWords(searchRequest: WordSearchRequest): List<ExrWordDto> {
        val listWords = search(WordSearchRequest(
            bookId = searchRequest.bookId,
            pageNum = searchRequest.pageNum,
        ))
        if(listWords.isNotEmpty()) {
            val lastNum = listWords.last().lineNum
            val startLine = if ((lastNum - 5) > 0) (lastNum - 5) else 0
            return listWords.filter { it.lineNum >= startLine }
        } else {
            return Collections.emptyList()
        }
    }

    override fun createPageWords(wordList: ExrWordListDto): List<ExrWordDto> {
        val result = mutableListOf<ExrWordDto>()
        wordList.wordList.forEach {
            if (!isWordExists(it)) {
                val wordEntity: ExrWordEntity = wordRepository.save(wordMapper.toEntity(it))
                result.add(wordMapper.toDto(wordEntity))
            }
        }
        return result
    }

    override fun deletePageWords(searchRequest: WordSearchRequest): Int {
        val result = wordRepository.deleteAllPageWords(bookId = searchRequest.bookId, pageNum = searchRequest.pageNum)
        return result
    }

    fun isWordExists(wordDto: ExrWordDto):Boolean {
        val listWords = search(WordSearchRequest(
            bookId = wordDto.book?.id,
            pageNum = wordDto.pageNum,
            lineNum = wordDto.lineNum,
            wordNum = wordDto.wordNum
        ))
        return listWords.isNotEmpty()
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
        val wordList = query.from(qWordEntity)
                .where(whereCause)
                .orderBy(qWordEntity.lineNum.asc())
                .orderBy(qWordEntity.wordNum.asc())
                .offset(0).limit(600)
                .fetch()
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