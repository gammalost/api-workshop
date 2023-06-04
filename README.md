

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

### 1.3 Legg til bruker

Lag et nytt `/user`-endpunkt for POST-kall der man kan sende med navn og alder for å lage en ny brukere.

Eksempel på kall er `/user?name=Ola Nordmann&age=20`

### (valgfri) 1.4 Slett bruker

Lag et DELETE-endepunkt for å slette en bruker ved å bruke navn

Eksempel på kall er `/user?name=Ola Nordmann`

## 2. Oppgave 2

I en service legger man forettningslogikk. Forettningslogikken sier noe om hva serveren skal gjøre når 
den får en forespørsel. Det kan f.eks. være å hente informasjon fra flere databaser og slå sammen resultatene til
en intern datamodell som klienten kan bruke.

### 2.1 Sorterte brukere

Endre på UserService slik at listen som blir returnert fra serveren blir sortert etter alder.

Hint: Det går å bruke [sortedBy](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/sorted-by.html) 
for å sortere en liste

## 3. Oppgave 3

HTTP status brukes for å si til klienten om en forespørsel er vellykket eller ikke. Her er et par vanlige eksempler:
* 200 OK - Forespørselen lyktes
* 404 Not found - Resursen ble ikke funnet av serveren

### 3.1 201 Created

Endre på post-endepunktet `/user` så at det gir tilbake 201 Created i stedet for 200 OK når man legger til en brukere

Hint: Det går å si at en respon skal ha http status så her:
```
return ResponseEntity.created(URI.create("/user")).build()
```

### 3.2 Resurs ikke funnet

Bruk `/user`-endepunktet fra 1.2 og sørg for at endepunktet gir tilbake en respons
med HTTP status "404 Not found" når man spør etter en brukere som ikke finnes.

Hint: Det går an å kaste en feilmelding med ønsket HTTP status med følgende kode:
```
throw ResponseStatusException(HttpStatus.NOT_FOUND)
```

Hint:  https://www.baeldung.com/spring-response-status-exception#1-generate-responsestatusexception

