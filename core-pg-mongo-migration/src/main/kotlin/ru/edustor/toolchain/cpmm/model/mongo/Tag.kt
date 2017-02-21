package ru.edustor.toolchain.cpmm.model.mongo

import java.time.Instant
import java.util.*

class Tag {
    var id: String = UUID.randomUUID().toString()
    lateinit var name: String
    var path: String = "/"
    var removedOn: Instant? = null
}