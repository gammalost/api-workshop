package no.bekk.apiworkshop.apiworkshop.oppgaver

import no.bekk.apiworkshop.apiworkshop.ApiWorkshopApplication
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
    fun `Oppgave 3,1 - Gi 404 Not Found ved henting av brukere som ikke finnes`() {
        mvc.get("/user/100")
            .andExpect { status { isNotFound() } }
    }
}
