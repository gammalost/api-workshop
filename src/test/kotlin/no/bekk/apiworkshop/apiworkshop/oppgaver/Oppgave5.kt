package no.bekk.apiworkshop.apiworkshop.oppgaver

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
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

    @Test
    fun `Oppgave 5,2 - Returner nytt objekt med epost`() {
        val brukere = mvc.get("/users").andReturn().response.contentAsString

        /* Noe keitete å sjekke om en spesifikk type er returnert når
         * den ikke er laget og kan brukes til deserialisering.
         * Ble en liten 360 no-scope på å lage strukturen selv og
         * sjekke to json-strukturer. Don't do this as home, kids 😅
         */
        val array = (Json.parseToJsonElement(brukere) as JsonArray).map { objekt ->
            buildJsonObject {
                put("id", (objekt as JsonObject)["id"].toString().toInt())
                put("name", (objekt as JsonObject)["name"].toString().replace("\"", ""))
                put("age", (objekt as JsonObject)["age"].toString().toInt())
                put("email", (objekt as JsonObject)["name"].toString().replace(" ", ".").replace("\"", "") + "@bekk.no")
            }
        }

        val brukereDetaljert = (
            Json.parseToJsonElement(
                mvc.get("/usersDetailed")
                    .andExpect { status { is2xxSuccessful() } }
                    .andReturn().response.contentAsString,
            ) as JsonArray
            )

        assert(JsonArray(array).containsAll(brukereDetaljert))
    }

    @Test
    @DirtiesContext
    fun `Oppgave 5,3 - Post med body`() {
        val name = "Emma Andersen"
        val age = 23
        mvc.post("/postUser") {
            content = """{"name":"$name","age":$age}"""
            contentType = MediaType.APPLICATION_JSON
        }
            .andExpect { status { is2xxSuccessful() } }
    }
}
