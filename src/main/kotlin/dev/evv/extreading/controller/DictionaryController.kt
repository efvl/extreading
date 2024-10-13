package dev.evv.extreading.controller

import dev.evv.extreading.dto.DictionaryDto
import dev.evv.extreading.dto.DictionarySearchRequest
import dev.evv.extreading.service.DictionaryService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(path = ["/v1/dict"])
@CrossOrigin
class DictionaryController(
    private val dictionaryService: DictionaryService
) {

    @PostMapping
    fun createDictionary(@RequestBody dictionaryDto: DictionaryDto): ResponseEntity<DictionaryDto> {
        return ResponseEntity.ok(dictionaryService.save(dictionaryDto)) ;
    }

    @GetMapping("/{id}")
    fun getDictionary(@PathVariable id: UUID) : ResponseEntity<DictionaryDto> {
        var dictionaryDto: DictionaryDto? = dictionaryService.getById(id);
        return ResponseEntity(dictionaryDto, HttpStatus.OK)
    }

    @PostMapping("/search")
    fun searchDictionaries(@RequestBody searchRequest: DictionarySearchRequest) : ResponseEntity<List<DictionaryDto>> {
        return ResponseEntity.ok().body(dictionaryService.search(searchRequest));
    }

    @PutMapping
    fun updateDictionary(@RequestBody dictionaryDto: DictionaryDto): ResponseEntity<DictionaryDto> {
        return ResponseEntity.ok(dictionaryService.updateDictionary(dictionaryDto))
    }

    @DeleteMapping("/{id}")
    fun deleteDictionary(@PathVariable id: UUID) {
        dictionaryService.deleteDictionaryById(id)
    }

}