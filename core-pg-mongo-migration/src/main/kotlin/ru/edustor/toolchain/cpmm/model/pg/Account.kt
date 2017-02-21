package ru.edustor.toolchain.cpmm.model.pg

import java.util.*
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id

@Entity
open class Account {
    @Id var id: String = UUID.randomUUID().toString()

    @ElementCollection(targetClass = String::class, fetch = FetchType.EAGER)
    val fcmTokens: MutableSet<String> = mutableSetOf()
}