package dev.evv.extreading.repository

import dev.evv.extreading.model.BookEntity
import dev.evv.extreading.model.DictionaryEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface DictionaryRepository : JpaRepository<DictionaryEntity, UUID>, QuerydslPredicateExecutor<DictionaryEntity> {

}