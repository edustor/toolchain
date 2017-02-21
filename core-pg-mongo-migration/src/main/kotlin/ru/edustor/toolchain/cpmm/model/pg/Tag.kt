package ru.edustor.toolchain.cpmm.model.pg

import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.time.Instant
import java.util.*
import javax.persistence.*

@Entity
open class Tag {

    @Id var id: String = UUID.randomUUID().toString()

    @Column(nullable = false)
    lateinit var name: String

    @ManyToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    lateinit var owner: Account

    @ManyToOne(optional = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    var parent: Tag? = null

    var removed: Boolean = false
    var removedOn: Instant? = null
}