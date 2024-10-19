package dev.evv.extreading.model

import jakarta.persistence.*

@Entity
@Table(name = "exr_word")
class ExrWordEntity(

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "book_id")
    var book: BookEntity?,

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "dictionary_id")
    var dictionary: DictionaryEntity?,

    ) : BaseIdEntity() {

}