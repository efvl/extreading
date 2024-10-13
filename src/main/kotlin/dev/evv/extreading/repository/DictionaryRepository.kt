package dev.evv.extreading.repository

import dev.evv.extreading.model.DictionaryEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface DictionaryRepository : JpaRepository<DictionaryEntity, UUID> {

}