

# api-workshop

Noe om arkitekturen og data som finnes i databasen?

## 1. Oppgave 1

Vi skal nå legge til mulighet for webserveren vår til gi ut informasjon om alle eller spesifikke brukere som er lagret i databasen. 
For å løse dette må webserveren ha to endepunkt for å hente informasjonen. Din jobb er å lage disse.
Siden vi kun skal hente data så ønsker vi å bruke [Get-kall](https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/GET).

### 1.1 Alle brukere

Lag endepunktet `/users` og hent data fra databasen.

### 1.2 Spesifikk bruker

Lag endepunktet `/user` og legg til mulighet for å spesifisere hvilken bruker som skal hentes med bruk av et request parameter `name`.

Eksempel på kall er `/user?name=Ola Nordmann`

Hint: https://www.baeldung.com/spring-request-param#a-simple-mapping

## 2. Oppgave 2

HTTP statuser brukes for å si til klienten om en forespørsel er vellykket eller ikke. Her er et par vanlige eksempler:
* 200 OK - Forespørselen lyktes
* 404 Not found - Resursen ble ikke funnet av serveren

### 2.1 Resurs ikke funnet

Bruk `/user`-endepunktet fra 1.2 og sørg for at endepunktet gir tilbake en respons
med HTTP status "404 Not found" når man spør etter en brukere som ikke finnes.