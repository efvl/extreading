package dev.evv.extreading.controller

import dev.evv.extreading.dto.ExrWordDto
import dev.evv.extreading.dto.ExrWordListDto
import dev.evv.extreading.dto.WordSearchRequest
import dev.evv.extreading.service.WordService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(path = ["/v1/word"])
@CrossOrigin
class WordController(
    private val wordService: WordService
) {

    @PostMapping
    fun createWord(@RequestBody wordDto: ExrWordDto): ResponseEntity<ExrWordDto> {
        return ResponseEntity.ok(wordService.save(wordDto))
    }

    @GetMapping("/{id}")
    fun getWord(@PathVariable id: UUID) : ResponseEntity<ExrWordDto> {
        var wordDto: ExrWordDto? = wordService.getById(id)
        return ResponseEntity(wordDto, HttpStatus.OK)
    }

    @PostMapping("/search")
    fun searchWords(@RequestBody searchRequest: WordSearchRequest) : ResponseEntity<List<ExrWordDto>> {
        return ResponseEntity.ok().body(wordService.search(searchRequest))
    }

    @PostMapping("/search/page")
    fun searchPageWords(@RequestBody searchRequest: WordSearchRequest): ResponseEntity<List<ExrWordDto>> {
        return ResponseEntity.ok().body(
            wordService.getPageWords(WordSearchRequest(bookId = searchRequest.bookId, pageNum = searchRequest.pageNum)))
    }

    @PostMapping("/list")
    fun createWords(@RequestBody wordList: ExrWordListDto): ResponseEntity<List<ExrWordDto>> {
        return ResponseEntity.ok(wordService.createPageWords(wordList))
    }

    @PutMapping
    fun updateWord(@RequestBody wordDto: ExrWordDto): ResponseEntity<ExrWordDto> {
        return ResponseEntity.ok(wordService.updateWord(wordDto))
    }

    @DeleteMapping("/{id}")
    fun deleteWord(@PathVariable id: UUID) {
        wordService.deleteWordById(id)
    }

}