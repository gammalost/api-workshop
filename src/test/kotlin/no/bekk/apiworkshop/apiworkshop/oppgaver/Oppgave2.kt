package no.bekk.apiworkshop.apiworkshop.oppgaver

import no.bekk.apiworkshop.apiworkshop.ApiWorkshopApplication
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = [ApiWorkshopApplication::class],
)
@AutoConfigureMockMvc
class Oppgave2 {
    @Autowired
    private lateinit var mvc: MockMvc

    @Test
    @DirtiesContext
    fun `Oppgave 2,1 - Gi 201 Created når man legger til brukere`() {
        val name = "Gunde Svan"
        val age = 42
        mvc.post("/users") {
            content = """{"name":"$name","age":$age}"""
            contentType = MediaType.APPLICATION_JSON
        }
            .andExpect { status { isCreated() } }
    }

    @Test
    fun `Oppgave 2,2 - Gi 404 Not Found ved henting av brukere som ikke finnes`() {
        mvc.get("/users/100")
            .andExpect { status { isNotFound() } }
    }

    @Test
    fun `Oppgave 2,3 - Returnerer svar eller kaster feil ved deling på null`() {
        mvc.get("/divide1000by/100")
            .andExpect {
                status { isOk() }
                content { json("10") }
            }


        mvc.get("/divide1000by/0")
            .andExpect {
                status { isInternalServerError() }
            }
    }
}
