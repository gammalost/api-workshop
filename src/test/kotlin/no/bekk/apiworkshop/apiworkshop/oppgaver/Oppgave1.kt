package no.bekk.apiworkshop.apiworkshop.oppgaver

import kotlinx.serialization.json.Json
import no.bekk.apiworkshop.apiworkshop.ApiWorkshopApplication
import no.bekk.apiworkshop.apiworkshop.repository.User
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

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
        assert(result == "Hello world!")
    }

    @Test
    fun `Henter alle brukere i databasen`() {
        val result = mvc.get("/users")
            .andExpect { status { is2xxSuccessful() } }
            .andReturn().let {
                Json.decodeFromString<List<User>>(it.response.contentAsString)
            }
        assert(result.size == 10)
    }

    @Test
    fun `Hent spesifikk bruker fra database`() {
        val result = mvc.get("/user?name=Olav Olsen")
            .andExpect { status { is2xxSuccessful() } }
            .andReturn().let {
                Json.decodeFromString<User?>(it.response.contentAsString)
            }
        assert(result != null)
    }

    @Test
    fun `Legg til en ny bruker`() {
        val name = "Gunde Svan"
        val age = 42
        mvc.post("/user?name=$name&age=$age")
            .andExpect { status { is2xxSuccessful() } }
        val user = mvc.get("/user?name=$name")
            .andExpect { status { is2xxSuccessful() } }
            .andReturn().let {
                Json.decodeFromString<User?>(it.response.contentAsString)
            }
        assert(user != null)
        assert(user?.name == name)
        assert(user?.age == age)
    }
}
