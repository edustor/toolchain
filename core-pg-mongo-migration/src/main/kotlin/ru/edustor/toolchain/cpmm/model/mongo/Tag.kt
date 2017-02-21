package ru.edustor.toolchain.cpmm.model.mongo

import java.time.Instant
import java.util.*

class Tag {
    lateinit var name: String
    var id: String = UUID.randomUUID().toString()
    var path: String = "/"
    var removed: Boolean = false
    var removedOn: Instant? = null
}