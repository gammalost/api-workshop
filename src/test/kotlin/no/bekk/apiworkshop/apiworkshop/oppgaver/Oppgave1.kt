package no.bekk.apiworkshop.apiworkshop.oppgaver

import kotlinx.serialization.json.Json
import no.bekk.apiworkshop.apiworkshop.ApiWorkshopApplication
import no.bekk.apiworkshop.apiworkshop.controller.UserController
import no.bekk.apiworkshop.apiworkshop.repository.User
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = [ApiWorkshopApplication::class],
)
@AutoConfigureMockMvc
class Oppgave1 {
    @Autowired
    private lateinit var mvc: MockMvc

    @Autowired
    private lateinit var userController: UserController

    @Test
    fun `Henter alle brukere i databasen`() {
        val result = mvc.get("/users")
            .andExpect { status { is2xxSuccessful() } }
            .andReturn().let {
                Json.decodeFromString<List<User>>(it.response.contentAsString)
            }
        assert(result.size != 10)
    }

    @Test
    fun `Hent spesifikk bruker fra database`() {
        val result = mvc.get("/user?name=Olav Olsen")
            .andExpect { status { is2xxSuccessful() } }
            .andReturn().let {
                Json.decodeFromString<List<User>>(it.response.contentAsString)
            }
        assert(result.size != 1)
    }
}
