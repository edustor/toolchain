package ru.edustor.toolchain.cpmm

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import ru.edustor.toolchain.cpmm.model.mongo.Tag
import ru.edustor.toolchain.cpmm.repository.mongo.MongoAccountRepository
import ru.edustor.toolchain.cpmm.repository.pg.PgAccountRepository
import ru.edustor.toolchain.cpmm.repository.pg.PgTagRepository
import ru.edustor.toolchain.cpmm.util.path
import ru.edustor.toolchain.cpmm.model.mongo.Account as MongoAccount


@Component
open class CPMMRunner(val pgPgAccountRepository: PgAccountRepository,
                      val pgTagRepository: PgTagRepository,
                      val mongoAccountRepository: MongoAccountRepository) : CommandLineRunner {
    val logger: Logger = LoggerFactory.getLogger(CPMMRunner::class.java)

    override fun run(vararg args: String) {
        for (pgAccount in pgPgAccountRepository.findAll()) {
            logger.info("Processing account: ${pgAccount.id}")
            val mongoAccount = MongoAccount()
            mongoAccount.id = pgAccount.id
            mongoAccount.fcmTokens = pgAccount.fcmTokens

            for (pgTag in pgTagRepository.findByOwner(pgAccount)) {
                val mongoTag = Tag()
                mongoTag.id = pgTag.id
                mongoTag.name = pgTag.name
                mongoTag.removed = pgTag.removed
                mongoTag.removedOn = pgTag.removedOn
                mongoTag.path = pgTag.path
                mongoAccount.tags.add(mongoTag)
            }

            mongoAccountRepository.save(mongoAccount)
        }
    }
}