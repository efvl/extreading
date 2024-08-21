package dev.evv.extreading.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "language")
class LanguageEntity(

    @Column(name = "short_name")
    var shortName: String = "",

    @Column(name = "full_name")
    var fullName: String = "",

) : BaseIdEntity() {

}