package ru.edustor.toolchain.cpmm.model.mongo

import java.time.Instant
import java.util.*
import javax.persistence.Id

class Page {
    @Id var id: String = UUID.randomUUID().toString()
    var timestamp: Instant = Instant.now()
    var uploadTimestamp: Instant? = null
    var fileId: String? = null
    var contentType: String? = null
    var qr: String? = null
    var fileMD5: String? = null
    var removedOn: Instant? = null
}