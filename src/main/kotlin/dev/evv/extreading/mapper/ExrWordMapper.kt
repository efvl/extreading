package dev.evv.extreading.mapper

import dev.evv.extreading.dto.ExrWordDto
import dev.evv.extreading.model.ExrWordEntity
import org.mapstruct.Mapper

@Mapper(componentModel = "spring", uses = [BookMapper::class, DictionaryMapper::class])
interface ExrWordMapper {

    fun toDto(entity: ExrWordEntity): ExrWordDto

    fun toEntity(dto: ExrWordDto): ExrWordEntity

}