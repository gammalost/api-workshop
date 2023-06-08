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
class Oppgave3 {
    @Autowired
    private lateinit var mvc: MockMvc

    @Test
    fun `Oppgave 3,1 - Gir tilbake en sortert liste`() {
        val result = mvc.get("/users")
            .andExpect { status { is2xxSuccessful() } }
            .andReturn().let {
                Json.decodeFromString<List<User>>(it.response.contentAsString)
            }
        assert(result == result.sortedBy { it.age })
    }
}
