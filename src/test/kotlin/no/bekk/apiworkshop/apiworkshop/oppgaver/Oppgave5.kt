package no.bekk.apiworkshop.apiworkshop.oppgaver

import kotlinx.serialization.json.Json
import no.bekk.apiworkshop.apiworkshop.ApiWorkshopApplication
import no.bekk.apiworkshop.apiworkshop.repository.User
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.get
import kotlin.test.assertEquals

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = [ApiWorkshopApplication::class],
)
@AutoConfigureMockMvc
class Oppgave5 {
    @Autowired
    private lateinit var mvc: MockMvc

    @Test
    @DirtiesContext
    fun `Oppgave 5,1 - Slett bruker`() {
        val name = "Emma Andersen"
        mvc.delete("/user?name=$name")
            .andExpect { status { is2xxSuccessful() } }
        val result = mvc.get("/users")
            .andReturn()
            .let { Json.decodeFromString<List<User>>(it.response.contentAsString) }
        assertEquals(9, result.size)
    }
}
