// Application configuration & entry point

package comp2850.music.server

import comp2850.music.db.MusicDatabase
import comp2850.music.db.TestDatabase
import io.ktor.server.application.Application
import org.jetbrains.exposed.v1.jdbc.transactions.TransactionManager

// Main config for application

fun Application.module() {
    TransactionManager.defaultDatabase = MusicDatabase.db
    configureErrorHandling()
    configureTemplates()
    configureRouting()
}

// Alternative config, for use in tests

fun Application.testModule() {
    TestDatabase.create()
    TransactionManager.defaultDatabase = TestDatabase.db
    configureTemplates()
    configureRouting()
}

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}
