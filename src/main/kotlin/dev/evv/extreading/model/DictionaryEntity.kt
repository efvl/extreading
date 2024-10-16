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

    @Column(name = "txt_content")
    var txtContent: String = "",

    @Column(name = "grammar")
    var grammar: String = "",

    ) : BaseIdEntity() {

}