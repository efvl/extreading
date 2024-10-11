package dev.evv.extreading.mapper

import dev.evv.extreading.dto.BookDto
import dev.evv.extreading.model.BookEntity
import org.mapstruct.Mapper

@Mapper(componentModel = "spring", uses = [LanguageMapper::class])
interface BookMapper {

    fun toDto(entity: BookEntity): BookDto

    fun toEntity(dto: BookDto): BookEntity

}