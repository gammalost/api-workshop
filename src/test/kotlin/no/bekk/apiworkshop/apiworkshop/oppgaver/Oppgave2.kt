package no.bekk.apiworkshop.apiworkshop.oppgaver

import kotlinx.serialization.json.Json
import no.bekk.apiworkshop.apiworkshop.ApiWorkshopApplication
import no.bekk.apiworkshop.apiworkshop.repository.User
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import kotlin.test.assertEquals

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = [ApiWorkshopApplication::class],
)
@AutoConfigureMockMvc
class Oppgave2 {
    @Autowired
    private lateinit var mvc: MockMvc

    @Test
    fun `Oppgave 2,1 - Gi 200 OK ved henting av brukere finnes`() {
        mvc.get("/users/1")
            .andExpect { status { isOk() } }
    }

    @Test
    @DirtiesContext
    fun `Oppgave 2,2 - Legg til en ny bruker`() {
        val name = "Gunde Svan"
        val age = 42
        mvc.post("/users") {
            content = """{"name":"$name","age":$age}"""
            contentType = MediaType.APPLICATION_JSON
        }
            .andExpect { status { isCreated() } }
    }

    @Test
    fun `Oppgave 2,3 - Gi 404 Not Found ved henting av brukere som ikke finnes`() {
        mvc.get("/users/100")
            .andExpect { status { isNotFound() } }
    }
}
