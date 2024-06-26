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
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import kotlin.test.assertEquals

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = [ApiWorkshopApplication::class],
)
@AutoConfigureMockMvc
class Oppgave1 {
    @Autowired
    private lateinit var mvc: MockMvc

    @Test
    fun `Returnerer Hello world!`() {
        val result = mvc.get("/hello")
            .andExpect { status { is2xxSuccessful() } }
            .andReturn().response.contentAsString
        assertEquals("Hello world!", result)
    }

    @Test
    fun `Oppgave 1,1 - Henter alle brukere i databasen`() {
        val result = mvc.get("/users")
            .andExpect { status { is2xxSuccessful() } }
            .andReturn().let {
                Json.decodeFromString<List<User>>(it.response.contentAsString)
            }
        assertEquals(10, result.size)
    }

    @Test
    fun `Oppgave 1,2 - Hent spesifikk bruker fra database`() {
        val result = mvc.get("/users/1")
            .andExpect { status { is2xxSuccessful() } }
            .andReturn().let {
                Json.decodeFromString<User?>(it.response.contentAsString)
            }
        assert(result != null)
        assertEquals("Olav Olsen", result?.name)
    }

    @Test
    @DirtiesContext
    fun `Oppgave 1,3 - Legg til en ny bruker`() {
        val name = "Gunde Svan"
        val age = 42
        mvc.post("/users") {
            content = """{"name":"$name","age":$age}"""
            contentType = MediaType.APPLICATION_JSON
        }
            .andExpect { status { is2xxSuccessful() } }
        val user = mvc.get("/users/11")
            .andExpect { status { is2xxSuccessful() } }
            .andReturn().let {
                Json.decodeFromString<User?>(it.response.contentAsString)
            }
        assert(user != null)
        assertEquals(name, user?.name)
        assertEquals(age, user?.age)
    }
}
