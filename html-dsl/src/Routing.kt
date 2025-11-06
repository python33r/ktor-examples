import io.ktor.server.application.Application
import io.ktor.server.html.respondHtmlTemplate
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import io.ktor.server.util.getValue
import kotlinx.html.*

fun Application.configureRouting() {
    routing {
        get("/roll/d{sides}") {
            val sides: Int by call.parameters
            val number = dieRoll(sides)
            call.respondHtmlTemplate(LayoutTemplate()) {
                titleText { +"Die Roller" }
                content {
                    h1 { +"d$sides Die Roll" }
                    p { +"Result = $number" }
                }
            }
        }
    }
}
