// Example of using Exposed's DSL for executing SQL queries
// (See server for examples of using the DAO approach)

package comp2850.music.db

import org.jetbrains.exposed.v1.core.StdOutSqlLogger
import org.jetbrains.exposed.v1.jdbc.select
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

fun main(args: Array<String>) {
    val sqlLogging = args.isNotEmpty() && args[0].lowercase() == "--sql"

    transaction(MusicDatabase.db) {
        if (sqlLogging) {
            addLogger(StdOutSqlLogger)
        }

        val numArtists = ArtistTable.selectAll().count()
        val numAlbums = AlbumTable.selectAll().count()

        println("Artists found: $numArtists")
        println("Albums found: $numAlbums")
        println()

        val albums = (AlbumTable innerJoin ArtistTable).select(ArtistTable.name, AlbumTable.title)

        albums.forEach {
            val artist = it[ArtistTable.name]
            val album = it[AlbumTable.title]
            println("$artist - $album")
        }
    }
}
