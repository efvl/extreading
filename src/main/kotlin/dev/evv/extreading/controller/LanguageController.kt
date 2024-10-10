package dev.evv.extreading.controller

import dev.evv.extreading.dto.LanguageDto
import dev.evv.extreading.dto.LanguageSearchRequest
import dev.evv.extreading.service.LanguageService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(path = ["/v1/lang"])
@CrossOrigin
class LanguageController(
    private val languageService: LanguageService
) {

    @PostMapping
    fun createLanguage(@RequestBody languageDto:LanguageDto): ResponseEntity<LanguageDto> {
        return ResponseEntity.ok(languageService.save(languageDto)) ;
    }

    @GetMapping("/{id}")
    fun getLanguage(@PathVariable id:UUID) : ResponseEntity<LanguageDto> {
        var languageDto: LanguageDto? = languageService.getById(id);
        return ResponseEntity(languageDto, HttpStatus.OK)
    }

    @PostMapping("/search")
    fun searchLanguages(@RequestBody searchRequest:LanguageSearchRequest) : ResponseEntity<List<LanguageDto>> {
        return ResponseEntity.ok().body(languageService.search(searchRequest));
    }

    @PutMapping
    fun updateLanguage(@RequestBody languageDto: LanguageDto?): ResponseEntity<LanguageDto> {
        return ResponseEntity.ok(languageService.updateLanguage(languageDto))
    }

    @DeleteMapping("/{id}")
    fun deleteLanguage(@PathVariable id: UUID) {
        languageService.deleteLanguageById(id)
    }

}