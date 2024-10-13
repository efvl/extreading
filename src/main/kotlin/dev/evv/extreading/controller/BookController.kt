package dev.evv.extreading.controller

import dev.evv.extreading.dto.BookDto
import dev.evv.extreading.dto.BookSearchRequest
import dev.evv.extreading.service.BookService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(path = ["/v1/book"])
@CrossOrigin
class BookController(
    private val bookService: BookService
) {

    @PostMapping
    fun createBook(@RequestBody bookDto: BookDto): ResponseEntity<BookDto> {
        return ResponseEntity.ok(bookService.save(bookDto)) ;
    }

    @GetMapping("/{id}")
    fun getBook(@PathVariable id: UUID) : ResponseEntity<BookDto> {
        var bookDto: BookDto? = bookService.getById(id);
        return ResponseEntity(bookDto, HttpStatus.OK)
    }

    @PostMapping("/search")
    fun searchBooks(@RequestBody searchRequest: BookSearchRequest) : ResponseEntity<List<BookDto>> {
        return ResponseEntity.ok().body(bookService.search(searchRequest));
    }

    @PutMapping
    fun updateBook(@RequestBody bookDto: BookDto): ResponseEntity<BookDto> {
        return ResponseEntity.ok(bookService.updateBook(bookDto))
    }

    @DeleteMapping("/{id}")
    fun deleteBook(@PathVariable id: UUID) {
        bookService.deleteBookById(id)
    }

}