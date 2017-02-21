package ru.edustor.toolchain.cpmm

import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
open class CPMMRunner : CommandLineRunner {
    val logger = LoggerFactory.getLogger(CPMMRunner::class.java)

    override fun run(vararg args: String) {
        logger.info("Hello world")
    }
}