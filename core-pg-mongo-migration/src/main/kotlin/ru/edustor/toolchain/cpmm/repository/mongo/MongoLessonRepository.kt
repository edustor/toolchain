package ru.edustor.toolchain.cpmm.repository.mongo

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import ru.edustor.toolchain.cpmm.model.mongo.Account
import ru.edustor.toolchain.cpmm.model.mongo.Lesson

@Repository
interface MongoLessonRepository : MongoRepository<Lesson, String>