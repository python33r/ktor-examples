package comp2850.music.server

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.testApplication

@Suppress("unused")
class ApplicationTest : StringSpec({
    "Home page loads and reports artist & album counts correctly" {
        testApplication {
            application { testModule() }
            val response = client.get("/").also { checkForHtml(it) }
            response.bodyAsText().let {
                it shouldContain "<h1>Music Club</h1>"
                it shouldContain "<strong>2 artists</strong>"
                it shouldContain "<strong>3 albums</strong>"
            }
        }
    }

    "Artists page loads and lists artists correctly" {
        testApplication {
            application { testModule() }
            val response = client.get("/artists").also { checkForHtml(it) }
            response.bodyAsText().let {
                it shouldContain "<h2>Artists</h2>"
                it shouldContain "A Band"
                it shouldContain "Doe, John"
            }
        }
    }

    "Albums page loads and lists albums correctly" {
        testApplication {
            application { testModule() }
            val response = client.get("/albums").also { checkForHtml(it) }
            response.bodyAsText().let {
                it shouldContain "<h2>Albums</h2>"
                it shouldContain "An Album"
                it shouldContain "First Album"
                it shouldContain "Second Album"
            }
        }
    }
})

fun checkForHtml(response: HttpResponse) {
    response.status shouldBe HttpStatusCode.OK
    response.headers["Content-Type"]?.shouldContain("text/html")
}
