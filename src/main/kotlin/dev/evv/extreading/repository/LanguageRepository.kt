package dev.evv.extreading.repository

import dev.evv.extreading.model.LanguageEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface LanguageRepository : JpaRepository<LanguageEntity, UUID> {

}