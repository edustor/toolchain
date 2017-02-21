package ru.edustor.toolchain.cpmm.repository.pg

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.edustor.toolchain.cpmm.model.pg.Account
import ru.edustor.toolchain.cpmm.model.pg.Tag

@Repository
interface PgTagRepository : JpaRepository<Tag, String> {
    fun findByOwner(account: Account): List<Tag>
}