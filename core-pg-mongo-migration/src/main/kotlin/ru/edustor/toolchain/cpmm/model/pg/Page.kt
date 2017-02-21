package ru.edustor.toolchain.cpmm.model.pg

import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.time.Instant
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
open class Page() {

    @ManyToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    lateinit var owner: Account

    @Id var id: String = UUID.randomUUID().toString()

    
    @ManyToOne(optional = false)
    lateinit var lesson: Lesson

    @Column(nullable = false)
    var timestamp: Instant = Instant.now()

    @Column(nullable = false)
    var isUploaded: Boolean = false
    var fileId: String? = null
    var uploadedTimestamp: Instant? = null
    var qr: String? = null
    var contentType: String? = null
    @Column(name = "file_md5") var fileMD5: String? = null
    var removedOn: Instant? = null
    var index: Int = 0

    var removed: Boolean = false
        set(value) {
            field = value
            if (value) {
                removedOn = Instant.now()
            } else {
                removedOn = null
            }
        }

    constructor(qr: String?, owner: Account, timestamp: Instant, id: String) : this() {
        this.qr = qr
        this.owner = owner
        this.timestamp = timestamp
        this.id = id
    }

    constructor(qr: String?) : this() {
        this.qr = qr
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Page) return false
        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    fun toDTO(): PageDTO {
        return PageDTO(id, index, timestamp, isUploaded, uploadedTimestamp, qr, contentType, fileMD5, removed)
    }

    data class PageDTO(
            val id: String,
            val index: Int,
            val timestamp: Instant,
            val uploaded: Boolean,
            val uploadedTimestamp: Instant?,
            val qr: String?,
            val contentType: String?,
            val fileMD5: String?,
            val removed: Boolean
    )
}