package ru.edustor.toolchain.cpmm

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import ru.edustor.toolchain.cpmm.model.mongo.Lesson
import ru.edustor.toolchain.cpmm.model.mongo.Page
import ru.edustor.toolchain.cpmm.model.mongo.Tag
import ru.edustor.toolchain.cpmm.repository.mongo.MongoAccountRepository
import ru.edustor.toolchain.cpmm.repository.mongo.MongoLessonRepository
import ru.edustor.toolchain.cpmm.repository.pg.PgAccountRepository
import ru.edustor.toolchain.cpmm.repository.pg.PgLessonRepository
import ru.edustor.toolchain.cpmm.repository.pg.PgTagRepository
import ru.edustor.toolchain.cpmm.util.path
import ru.edustor.toolchain.cpmm.model.mongo.Account as MongoAccount


@Component
open class CPMMRunner(val pgAccountRepository: PgAccountRepository,
                      val pgTagRepository: PgTagRepository,
                      val pgLessonRepository: PgLessonRepository,
                      val mongoAccountRepository: MongoAccountRepository,
                      val mongoLessonRepository: MongoLessonRepository) : CommandLineRunner {
    val logger: Logger = LoggerFactory.getLogger(CPMMRunner::class.java)

    override fun run(vararg args: String) {
        migrateAccounts()
        migrateLessons()
    }

    fun migrateAccounts() {
        for (pgAccount in pgAccountRepository.findAll()) {
            logger.info("Migrating account: ${pgAccount.id}")
            val mongoAccount = MongoAccount()
            mongoAccount.id = pgAccount.id
            mongoAccount.fcmTokens = pgAccount.fcmTokens

            for (pgTag in pgTagRepository.findByOwner(pgAccount)) {
                logger.info("Migrating tag: ${pgTag.id}")
                val mongoTag = Tag()
                mongoTag.id = pgTag.id
                mongoTag.name = pgTag.name
                mongoTag.removedOn = pgTag.removedOn
                mongoTag.path = pgTag.path
                mongoAccount.tags.add(mongoTag)
            }
            mongoAccountRepository.save(mongoAccount)
        }
    }

    @Suppress("ConvertLambdaToReference")
    fun migrateLessons() {
        for (pgLesson in pgLessonRepository.findAll()) {
            logger.info("Migrating lesson: ${pgLesson.id}")
            val mongoLesson = Lesson()
            mongoLesson.id = pgLesson.id
            mongoLesson.date = pgLesson.date
            mongoLesson.removedOn = pgLesson.removedOn
            mongoLesson.tagId = pgLesson.tag.id
            mongoLesson.topic = pgLesson.topic

            for (pgPage in pgLesson.pages.sortedBy { it.index }) {
                logger.info("Migrating page: ${pgPage.id}")
                val mongoPage = Page()
                mongoPage.id = pgPage.id
                mongoPage.timestamp = pgPage.timestamp
                mongoPage.uploadTimestamp = pgPage.uploadedTimestamp
                mongoPage.contentType = pgPage.contentType
                mongoPage.fileId = pgPage.fileId
                mongoPage.fileMD5 = pgPage.fileMD5
                mongoPage.qr = pgPage.qr
                mongoPage.removedOn = pgPage.removedOn

                mongoLesson.pages.add(mongoPage)
            }

            mongoLessonRepository.save(mongoLesson)
        }


    }
}