// Request handlers for Music Club home page
// (See also index.peb and search.peb in resources/templates)

package comp2850.music.server

import comp2850.music.db.Album
import comp2850.music.db.Albums
import comp2850.music.db.Artist
import io.ktor.server.application.ApplicationCall
import io.ktor.server.pebble.respondTemplate
import io.ktor.server.request.receiveParameters
import org.jetbrains.exposed.v1.core.like
import org.jetbrains.exposed.v1.jdbc.transactions.suspendTransaction

suspend fun ApplicationCall.homePage() {
    suspendTransaction {
        val data = mapOf("albums" to Album.count(), "artists" to Artist.count())
        respondTemplate("index.peb", data)
    }
}

suspend fun ApplicationCall.searchResults() {
    suspendTransaction {
        val formParams = receiveParameters()
        val searchTerm = formParams["search_term"] ?: ""
        val results = Album.find { Albums.title like "%$searchTerm%" }
        val data = mapOf("searchTerm" to searchTerm, "results" to results)
        respondTemplate("search.peb", data)
    }
}
