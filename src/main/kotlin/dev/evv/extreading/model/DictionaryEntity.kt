package dev.evv.extreading.model

import jakarta.persistence.*

@Entity
@Table(name = "dictionary")
class DictionaryEntity(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lang_id")
    var language: LanguageEntity,

    @Column(name = "base_form")
    var baseForm: String = "",

    @Column(name = "definition")
    var definition: String = "",

    @Column(name = "example1")
    var example1: String = "",

    @Column(name = "example2")
    var example2: String = "",

    @Column(name = "info")
    var info: String = "",

    ) : BaseIdEntity() {

}