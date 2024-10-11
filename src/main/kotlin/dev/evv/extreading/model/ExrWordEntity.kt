package dev.evv.extreading.model

import jakarta.persistence.*

@Entity
@Table(name = "exr_word")
class ExrWordEntity(

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    var book: BookEntity,

    @Column(name = "page_num")
    var pageNum: Int = 0,

    @Column(name = "line_num")
    var lineNum: Int = 0,

    @Column(name = "word_num")
    var wordNum: Int = 0,

    @Column(name = "original")
    var original: String = "",

    @Column(name = "txt_content")
    var txtContent: String = "",

    @Column(name = "grammar")
    var grammar: String = "",

    @Column(name = "info")
    var info: String = "",

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dictionary_id")
    var dictionary: DictionaryEntity,

    ) : BaseIdEntity() {

}