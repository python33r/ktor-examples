// Database schema for Albums table

package comp2850.music.db

import org.jetbrains.exposed.v1.core.dao.id.UIntIdTable
import org.jetbrains.exposed.v1.core.ReferenceOption

object Albums: UIntIdTable() {
    val artist = reference("artist_id", Artists, ReferenceOption.CASCADE)
    val title = varchar("title", MAX_VARCHAR_LENGTH)
    val year = integer("year")
    val youtube = varchar("youtube_url", MAX_VARCHAR_LENGTH).nullable()
}
