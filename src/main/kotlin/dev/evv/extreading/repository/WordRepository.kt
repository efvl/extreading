package dev.evv.extreading.repository

import dev.evv.extreading.model.ExrWordEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface WordRepository : JpaRepository<ExrWordEntity, UUID> {

}