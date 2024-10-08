package dev.evv.extreading.controller

import dev.evv.extreading.dto.LanguageDto
import dev.evv.extreading.dto.LanguageSearchRequest
import dev.evv.extreading.service.LanguageService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping(path = ["/v1/lang"])
@CrossOrigin
class LanguageController(
    private val languageService: LanguageService
) {

    @PostMapping
    fun createLanguage(@RequestBody languageDto:LanguageDto): ResponseEntity<LanguageDto> {
        println(languageDto)
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

}