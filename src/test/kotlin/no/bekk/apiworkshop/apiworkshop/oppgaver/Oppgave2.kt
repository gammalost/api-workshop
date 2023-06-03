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

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = [ApiWorkshopApplication::class],
)
@AutoConfigureMockMvc
class Oppgave2 {
    @Autowired
    private lateinit var mvc: MockMvc

    val sorterteBrukere = listOf(
        User(id = 0, name = "Emma Andersen", age = 22),
        User(id = 0, name = "Mia Solberg", age = 25),
        User(id = 0, name = "Sofie Kristoffersen", age = 28),
        User(id = 0, name = "Henrik Larsen", age = 31),
        User(id = 0, name = "Ingrid Johansen", age = 34),
        User(id = 0, name = "Nora Berg", age = 40),
        User(id = 0, name = "Olav Olsen", age = 42),
        User(id = 0, name = "Magnus Eriksen", age = 46),
        User(id = 0, name = "Lars Nilsen", age = 53),
        User(id = 0, name = "William Carlsen", age = 56),
    )

    @Test
    fun `Gir tilbake en sortert liste`() {
        val result = mvc.get("/users")
            .andExpect { status { is2xxSuccessful() } }
            .andReturn().let {
                Json.decodeFromString<List<User>>(it.response.contentAsString)
            }
        result.zip(sorterteBrukere).forEach {
            assert(it.first.age == it.second.age)
            assert(it.first.name == it.second.name)
        }
    }
}
