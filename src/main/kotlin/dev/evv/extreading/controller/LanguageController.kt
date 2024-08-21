package dev.evv.extreading.controller

import dev.evv.extreading.dto.LanguageDto
import dev.evv.extreading.service.LanguageService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

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

}