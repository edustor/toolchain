package ru.edustor.toolchain.cpmm.model.mongo

import org.springframework.data.mongodb.core.mapping.Document
import ru.edustor.toolchain.cpmm.model.pg.Account
import java.time.Instant
import java.time.LocalDate
import java.util.*

@Document
class Lesson {
    var id: String = UUID.randomUUID().toString()
    lateinit var ownerId: String
    lateinit var tagId: String
    lateinit var date: LocalDate
    var topic: String? = null
    var removedOn: Instant? = null
    var pages: MutableList<Page> = mutableListOf()
}