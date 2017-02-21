package ru.edustor.toolchain.cpmm.repository.pg

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.edustor.toolchain.cpmm.model.pg.Account

@Repository
interface PgAccountRepository : JpaRepository<Account, String> {

}