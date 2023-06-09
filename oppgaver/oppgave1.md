# Oppgave 1 - HTTP-kall

Det finnes flere forskjellige http-forespørsler med forskjellige funksjoner.
Vi skal nå se nærmere på de vanligste og lage noen enkle endepunkt for hver av de.

Beskrivelse av de ulike finner du her: https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods

## 1 - Hent alle brukere

Vi har laget et database med brukere for denne workshopen. Innholdet i denne finner du i `data.sql`.
For denne oppgaven skal vi lage et endepunkt som returnerer alle brukerne fra databasen. Siden vi ønsker å hente informasjon så skal vi bruke et [GET](https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/GET)-kall.

**Oppgave**: Lag endepunktet `/users` i `UserController.kt` og hent data fra databasen.


<details>
<summary>Hint 🕵️ 📜</summary>

Gå til http://localhost:8080/users og sjekk hva du får tilbake.
I stedet for å lime det inn i nettleseren kan man bruke Postman som er litt diggere.  [Les mer om Postman her](#postman).

</details>

🧪 Når du er ferdig, kjør testene i `Oppgave 1` og sjekk at testen `Oppgave 1,1` fungerer

## 2 - Hent en spesifikk bruker

Ofte ønsker vi å ha mulighet til å hente en spesifikk bruker. Da må vi sende med informasjon om hvilken bruker vi ønsker.
For å spesifisere dette kan vi bruke en path parameter i url-en, eks. hente vekommende med ansattnummer 1000: `bekk.no/ansatte/1000`

**Oppgave**: Lag endepunktet `/user` og legg til mulighet for å spesifisere id-en til brukeren vi ønsker returnert med path parameteret `id`.

Eksempel på kall: `/user/1`

<details>
<summary>Hint 🕵️ 📜</summary>

Hvordan å håndtere path parametre: https://www.baeldung.com/spring-pathvariable
</details>

## 3 - Legg til en bruker

Det å kun ha mulighet til å hente informasjon er en start, men det blir et lite dynamisk system.
For å gjøre det mer brukbart skal du nå gjøre det mulig å legge til brukere.
For dette skal vi bruke et [POST](https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/POST)-kall.

**Oppgave**: Lag endepunktet `/user` som tar inn `name` og `age` som request parametre som skal brukes til å opprette brukeren.

Eksempel på kall: `/user?name=Ola Nordmann&age=20`

<details>
<summary>Hint 🕵️ 📜</summary>

Hvordan å håndtere request params: https://www.baeldung.com/spring-request-param
</details>
