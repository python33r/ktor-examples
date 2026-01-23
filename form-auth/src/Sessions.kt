// Set up session cookies

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.sessions.SessionTransportTransformerEncrypt
import io.ktor.server.sessions.Sessions
import io.ktor.server.sessions.cookie
import io.ktor.util.hex
import java.io.File
import kotlinx.serialization.Serializable

const val KEYS_FILENAME = "session_keys.txt"

@Serializable
data class UserSession(val username: String, val count: Int)

fun Application.configureSessions() {
    val (encryptKey, signKey) = readKeys(KEYS_FILENAME)   // OK for demos
    install(Sessions) {
        cookie<UserSession>("user_session") {
            cookie.path = "/"
            cookie.maxAgeInSeconds = 60
            transform(SessionTransportTransformerEncrypt(encryptKey, signKey))
        }
    }
}

fun readKeys(filename: String): Pair<ByteArray,ByteArray> {
    val lines = File(filename).readLines()
    val encryptKey = hex(lines[0])
    val signKey = hex(lines[1])
    return Pair(encryptKey, signKey)
}
