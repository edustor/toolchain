package ru.edustor.toolchain.cpmm.model.pg

import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.time.Instant
import java.time.LocalDate
import java.util.*
import javax.persistence.*

@Entity
open class Lesson {
    @Id var id: String = UUID.randomUUID().toString()

    @ManyToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    lateinit var tag: Tag

    @Column(nullable = false)
    lateinit var date: LocalDate

    @ManyToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    lateinit var owner: Account

    var topic: String? = null

    @OrderBy("index ASC")
    @OneToMany(mappedBy = "lesson", cascade = arrayOf(CascadeType.REMOVE), fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    var pages: MutableList<Page> = mutableListOf()

    var removedOn: Instant? = null

    var removed: Boolean = false
}