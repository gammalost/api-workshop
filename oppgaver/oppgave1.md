# Oppgave 1 - HTTP-kall

I denne oppgaven skal vi utforske hvordan vi kan lage enkle API-endepunkter ved hjelp av de vanligste HTTP-forespørsler.
En oversikt over de ulike HTTP-forespørslene finner du på [mdn web docs](https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods).

## 1 - Hent alle brukere

Vi har laget et database med brukere for denne workshopen. Innholdet i denne finner du i [data.sql](../src/main/resources/data.sql).
For denne oppgaven skal vi lage et API som returnerer alle brukerne fra databasen. Siden vi ønsker å hente informasjon så skal vi bruke et [GET](https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/GET)-kall.

**Oppgave**: Lag endepunktet `/users` i `UserController.kt` og hent data fra databasen.

Ta inspirasjon fra helloWorld-funksjonen! Gå til http://localhost:8080/users og sjekk hva du får tilbake. I `UserService.kt` kan du bruke `userRepository.getUsers()` for å hente informasjon fra databasen.

🧪 Når du er ferdig, kjør testene i `Oppgave1` og sjekk at testen `Oppgave 1,1` kjører grønt.

## 2 - Hent en spesifikk bruker

Ofte ønsker vi å ha mulighet til å hente en spesifikk bruker. Da må vi sende med informasjon om hvilken bruker vi ønsker.
For å spesifisere dette kan vi bruke en path parameter i url-en, eks. hente vekommende med ansattnummer 1000: `bekk.no/ansatte/1000`

**Oppgave**: Lag endepunktet `/users` og legg til mulighet for å spesifisere id-en til brukeren vi ønsker returnert med path parameteret `id`.

Les om hvordan path parametre fungerer her: https://www.baeldung.com/spring-pathvariable

Eksempel på kall: `http://localhost:8080/users/1`



## 3 - Legg til en bruker

Det å kun ha mulighet til å hente informasjon er en start, men det blir et lite dynamisk system.
For å gjøre det mer brukbart skal du nå gjøre det mulig å legge til brukere.
For dette skal vi bruke et [POST](https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/POST)-kall.

I POST-kall blir vanligvis data sendt med i body-en til forespørselen. 
Les om hvordan man kan lese informasjon fra request body i [dokumentasjonen til Spring](https://docs.spring.io/spring-framework/reference/web/webflux/controller/ann-methods/requestbody.html).



**Oppgave**: Lag endepunktet `/users` som tar inn `name` og `age` som request body som skal brukes til å opprette brukeren.

For å teste at dette kan du bruke [curl](https://curl.se/) i terminalen. Eksempel på post-kall der du sender med en body i json-format:
```
curl -X POST -H "Content-Type: application/json" -d '{"name": "Ola Nordmann", "age": 30}' http://localhost:8080/users
```

<details>
<summary>Hint 🕵️ 📜</summary>

---

Lag en dataklasse som representerer dataen som skal sendes inn i POST-kallet.
```
data class UserDTO(val name: String, val age: Int)
```

Når du har User-klassen på plass kan du bruke `@RequestBody` for å lese dataen som sendes inn i POST-kallet, på lik linje
som du brukte `@PathVariable` for å lese path parametre.

---
</details>
