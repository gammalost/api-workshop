# api-workshop

Noe om arkitekturen og data som finnes i databasen?

## Oppgave 1
Vi skal nå legge til mulighet for webserveren vår til gi ut informasjon om alle eller spesifikke brukere som er lagret i databasen. 
For å løse dette må webserveren ha to endepunkt for å hente informasjonen. Din jobb er å lage disse.
Siden vi kun skal hente data så ønsker vi å bruke [Get-kall](https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/GET).

### Alle brukere
Lag endepunktet `/users` og hent data fra databasen.

### Spesifikk bruker
Lag endepunktet `/user` og legg til mulighet for å spesifisere hvilken bruker som skal hentes med bruk av et request parameter `name`.

Eksempel på kall er `/user?name=Ola Nordmann`

Hint: https://www.baeldung.com/spring-request-param#a-simple-mapping