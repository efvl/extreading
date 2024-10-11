package dev.evv.extreading.mapper

import dev.evv.extreading.dto.DictionaryDto
import dev.evv.extreading.model.DictionaryEntity
import org.mapstruct.Mapper

@Mapper(componentModel = "spring", uses = [LanguageMapper::class])
interface DictionaryMapper {

    fun toDto(entity: DictionaryEntity): DictionaryDto

    fun toEntity(dto: DictionaryDto): DictionaryEntity

}