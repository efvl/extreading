package dev.evv.extreading.service

import com.querydsl.jpa.impl.JPAQueryFactory
import dev.evv.extreading.dto.BookDto
import dev.evv.extreading.dto.BookSearchRequest
import dev.evv.extreading.exception.BookNotFoundException
import dev.evv.extreading.mapper.BookMapper
import dev.evv.extreading.model.BookEntity
import dev.evv.extreading.model.QBookEntity
import dev.evv.extreading.repository.BookRepository
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class BookServiceImpl (
    private val bookRepository: BookRepository,
    private val bookMapper: BookMapper,
    private val queryFactory: JPAQueryFactory
) : BookService {

    override fun save(bookDto: BookDto): BookDto {
        val bookEntity: BookEntity = bookRepository.save(bookMapper.toEntity(bookDto))
        return bookMapper.toDto(bookEntity)
    }

    override fun getById(id: UUID): BookDto {
        val bookEntity: BookEntity = bookRepository.findById(id).orElseThrow{ BookNotFoundException(id) }
        return bookMapper.toDto(bookEntity)
    }

    override fun search(searchRequest: BookSearchRequest): List<BookDto> {
        val qBookEntity = QBookEntity.bookEntity
        val query = queryFactory.selectFrom(qBookEntity)


        return bookRepository.findAll().stream()
            .map(bookMapper::toDto)
            .collect(Collectors.toList())
    }

    override fun deleteBookById(id: UUID) {
        bookRepository.deleteById(id)
    }

    override fun updateBook(bookDto: BookDto): BookDto {
        var updated = bookMapper.toDto(bookRepository.save(bookMapper.toEntity(bookDto)))
        return updated
    }
}