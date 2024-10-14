package dev.evv.extreading.repository

import dev.evv.extreading.model.BookEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BookRepository : JpaRepository<BookEntity, UUID>, QuerydslPredicateExecutor<BookEntity> {


}