package dev.evv.extreading.model

import jakarta.persistence.*

@Entity
@Table(name = "book")
class BookEntity(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lang_id")
    var language: LanguageEntity,

    @Column(name = "title")
    var title: String = "",

    @Column(name = "authors")
    var authors: String = "",

    @Column(name = "year")
    var year: Int = 0,

    @Column(name = "info")
    var info: String = "",

    ) : BaseIdEntity() {

}