package ru.edustor.toolchain.cpmm

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import ru.edustor.toolchain.cpmm.repository.mongo.MongoAccountRepository
import ru.edustor.toolchain.cpmm.repository.pg.PgAccountRepository
import ru.edustor.toolchain.cpmm.model.mongo.Account as MongoAccount


@Component
open class CPMMRunner(val pgPgAccountRepository: PgAccountRepository,
                      val mongoAccountRepository: MongoAccountRepository) : CommandLineRunner {
    val logger: Logger = LoggerFactory.getLogger(CPMMRunner::class.java)

    override fun run(vararg args: String) {
        for (pgAccount in pgPgAccountRepository.findAll()) {
            logger.info("Processing account: ${pgAccount.id}")
            val mongoAccount = MongoAccount()
            mongoAccount.fcmTokens = pgAccount.fcmTokens
            mongoAccountRepository.save(mongoAccount)
        }
    }
}