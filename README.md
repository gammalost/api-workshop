

# api-workshop

Noe om arkitekturen og data som finnes i databasen?

## 1. Oppgave 1 - HTTP-kall

Det finnes flere forskjellige http-forespørsler med forskjellige funksjoner. 
Vi skal nå se nærmere på de vanligste og lage noen enkle endepunkt for hver av de.

Beskrivelse av de ulike finner du her: https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods

### 1.1 Hent alle brukere

Vi har laget et database med brukere for denne workshopen. Innholdet i denne finner du i `data.sql`.
For denne oppgaven skal vi lage et endepunkt som returnerer alle brukerne fra databasen. Siden vi ønsker å hente informasjon så skal vi bruke et [GET](https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/GET)-kall.

Lag endepunktet `/users` og hent data fra databasen.

### 1.2 Hent en spesifikk bruker

Ofte ønsker vi å ha mulighet til å hente en spesifikk bruker. Da må vi sende med informasjon om hvilken bruker vi ønsker.
For å spesifisere dette kan vi bruke path parametre i url-en.

Lag endepunktet `/user` og legg til mulighet for å spesifisere navnet på brukeren vi ønsker returnert med path parameteret `name`.

Eksempel på kall: `/user?name=Ola Nordmann`

Hint for hvordan å håndtere path parametre: https://www.baeldung.com/spring-request-param#a-simple-mapping

### 1.3 Legg til en bruker

Det å kun ha mulighet til å hente informasjon er en start, men det blir et lite dynamisk system. 
For å gjøre det mer brukbart skal du nå gjøre det mulig å legge til brukere.
For dette skal vi bruke et [POST](https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/POST)-kall.

Lag endepunktet `/user` som tar inn `name` og `age` som path parametre som skal brukes til å opprette brukeren.

Eksempel på kall: `/user?name=Ola Nordmann&age=20`

### 1.4 Slett en bruker (valgfritt)

Det å kunne legge til og hente brukere tar oss et stykke, men vi mangler enda mulighet for å slette en bruker.
For å slette en bruker skal vi gjøre et [DELETE](https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/DELETE)-kall.

Lag endepunktet `/user` som tar inn navnet på brukeren med path parameteret `name` og slett brukeren.

Eksempel på kall: `/user?name=Ola Nordmann`

## 2. Oppgave 2 - HTTP statuser

HTTP status brukes for å si til klienten om en forespørsel er vellykket eller ikke. Her er et par vanlige eksempler:
* 200 OK - Forespørselen lyktes
* 404 Not found - Resursen ble ikke funnet av serveren

### 2.1 201 Created

Endre på post-endepunktet `/user` så at det gir tilbake 201 Created i stedet for 200 OK når man legger til en brukere

Hint: Det går å si at en respon skal ha http status så her:
```
return ResponseEntity.status(HttpStatus.CREATED).build()
```

## 3. Oppgave 3 - Forettningslogikk

I en service legger man forettningslogikk. Forettningslogikken sier noe om hva serveren skal gjøre når
den får en forespørsel. Det kan f.eks. være å hente informasjon fra flere databaser og slå sammen resultatene til
en intern datamodell som klienten kan bruke.

### 3.1 Sorterte brukere

Endre på UserService slik at listen som blir returnert fra serveren blir sortert etter alder.

Hint: Det går å bruke [sortedBy](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/sorted-by.html)
for å sortere en liste

## 4. Oppgave 4 - Exceptions

Når noe går galt er det fint å si ifra om det til klienten. Her er noen eksempler:
* 400 Bad Request - klienten har en ugyldig spørring, f.eks. har man kanskje ikke sendt med alle request params som er påkrevd
* 404 Not Found - man gjør en spørring mot en URL som ikke finnes
* 500 Internal Server Error - Noe uventet intreffet

### 4.1 Ressurs ikke funnet

Bruk `/user`-endepunktet fra 1.2 og sørg for at endepunktet gir tilbake en respons
med HTTP status "404 Not found" når man spør etter en brukere som ikke finnes.

Hint: Det går an å kaste en feilmelding med ønsket HTTP status med følgende kode:
```
throw ResponseStatusException(HttpStatus.NOT_FOUND)
```

Hint:  https://www.baeldung.com/spring-response-status-exception#1-generate-responsestatusexception

### 4.2 Beskrivelse av feilen (valgfritt)

Legg til en bekskrivelse når det kastes en exception

