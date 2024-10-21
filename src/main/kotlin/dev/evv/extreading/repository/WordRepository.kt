package dev.evv.extreading.repository

import dev.evv.extreading.model.ExrWordEntity
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface WordRepository : JpaRepository<ExrWordEntity, UUID>, QuerydslPredicateExecutor<ExrWordEntity> {

    @Transactional
    @Modifying
    @Query("DELETE FROM ExrWordEntity ew WHERE ew.book.id = :bookId and ew.pageNum = :pageNum")
    fun deleteAllPageWords(bookId: UUID?, pageNum: Int?): Int

    @Transactional
    @Query("SELECT COUNT(ew) FROM ExrWordEntity ew WHERE ew.book.id = :bookId and ew.txtContent = :txtContent")
    fun countWordsInBook(bookId: UUID?, txtContent: String?): Int

}