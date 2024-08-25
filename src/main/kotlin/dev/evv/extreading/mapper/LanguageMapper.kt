package dev.evv.extreading.mapper

import dev.evv.extreading.dto.LanguageDto
import dev.evv.extreading.model.LanguageEntity
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface LanguageMapper {

    fun toDto(entity: LanguageEntity): LanguageDto

    fun toEntity(dto: LanguageDto): LanguageEntity

}