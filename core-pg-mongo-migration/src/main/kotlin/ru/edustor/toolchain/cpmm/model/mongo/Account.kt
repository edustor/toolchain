package ru.edustor.toolchain.cpmm.model.mongo

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document
class Account {
    @Id var id: String = UUID.randomUUID().toString()
    var fcmTokens: MutableSet<String> = mutableSetOf()
    var tags: MutableList<Tag> = mutableListOf()
}