package no.bekk.apiworkshop.apiworkshop.oppgaver

import no.bekk.apiworkshop.apiworkshop.ApiWorkshopApplication
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
class Oppgave2 {
    @Autowired
    private lateinit var mvc: MockMvc

    @Test
    fun `Gi 200 OK ved henting av brukere finnes`() {
        mvc.get("/user?name=Olav Olsen")
            .andExpect { status { isOk() } }
    }

    @Test
    fun `Gi 201 Created når man lager en ny brukere`() {
        val name = "Gunde Svan"
        val age = 42
        mvc.post("/user?name=$name&age=$age")
            .andExpect { status { is2xxSuccessful() } }
    }
}
