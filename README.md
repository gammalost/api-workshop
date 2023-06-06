# API-workshop

Backendtjenester kommer i mange former og fasonger, fra kjernebanksystemer som holder styr på kontoen din til webservere som håndterer forespørsler og returnerer informasjon.
Vi skal i denne workshopen se næmere på sistnevnte og sammen designe noen enkle API.


Enkel skisse over arkitekturen til backenden du skal jobbe med:
![Akritektur](./arkitektur.png)

Det er også satt opp en enkel, in memory [H2](https://www.h2database.com/html/main.html)-database.
For testene er denne denne fyllt med litt innhold. Hva kan du se i denne filen [data.sql](src/test/resources/data.sql).

## 1. Oppgave 1 - HTTP-kall

Det finnes flere forskjellige http-forespørsler med forskjellige funksjoner. 
Vi skal nå se nærmere på de vanligste og lage noen enkle endepunkt for hver av de.

Beskrivelse av de ulike finner du her: https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods

### 1.1 Hent alle brukere

Vi har laget et database med brukere for denne workshopen. Innholdet i denne finner du i `data.sql`.
For denne oppgaven skal vi lage et endepunkt som returnerer alle brukerne fra databasen. Siden vi ønsker å hente informasjon så skal vi bruke et [GET](https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/GET)-kall.

**Oppgave**: Lag endepunktet `/users` og hent data fra databasen.

### 1.2 Hent en spesifikk bruker

Ofte ønsker vi å ha mulighet til å hente en spesifikk bruker. Da må vi sende med informasjon om hvilken bruker vi ønsker.
For å spesifisere dette kan vi bruke path parametre i url-en.

**Oppgave**: Lag endepunktet `/user` og legg til mulighet for å spesifisere navnet på brukeren vi ønsker returnert med path parameteret `name`.

Eksempel på kall: `/user?name=Ola Nordmann`

Hint for hvordan å håndtere path parametre: https://www.baeldung.com/spring-request-param#a-simple-mapping

### 1.3 Legg til en bruker

Det å kun ha mulighet til å hente informasjon er en start, men det blir et lite dynamisk system. 
For å gjøre det mer brukbart skal du nå gjøre det mulig å legge til brukere.
For dette skal vi bruke et [POST](https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/POST)-kall.

**Oppgave**: Lag endepunktet `/user` som tar inn `name` og `age` som path parametre som skal brukes til å opprette brukeren.

Eksempel på kall: `/user?name=Ola Nordmann&age=20`

### 1.4 Slett en bruker (valgfritt)

Det å kunne legge til og hente brukere tar oss et stykke, men vi mangler enda mulighet for å slette en bruker.
For å slette en bruker skal vi gjøre et [DELETE](https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/DELETE)-kall.

**Oppgave**: Lag endepunktet `/user` som tar inn navnet på brukeren med path parameteret `name` og slett brukeren.

Eksempel på kall: `/user?name=Ola Nordmann`

## 2. Oppgave 2 - HTTP statuser

Som at det finnes flere forskjellige HTTP-kall så finnes det også forskjellige HTTP-statuser man kan få tilbake. 
Disse brukes for å si noe om et kall gikk bra eller noe feilet.
Listen over statuskodene og hva de alle betyr kan du lese mer om her: https://developer.mozilla.org/en-US/docs/Web/HTTP/Status

Vi skal fokusere på noen av de mer vanlige, som er 2xx, 4xx og 5xx.

### 2.1 201 Created

**Oppgave**: Endre på POST-endepunktet `/user` så det gir tilbake 201 Created i stedet for 200 OK når man legger til en bruker.

Hint for hvordan å returnere en spesifikk HTTP-status:
```
return ResponseEntity.status(HttpStatus.CREATED).build()
```

## 3. Oppgave 3 - Forretningslogikk

Til nå har vi sett på endepunktene til webserveren, men ofte ønsker vi også å gjøre noe mer som et resultat av kallene som kommer inn.
Dette kan være som vi i tidligere oppgaver har gjort med henting av data fra en database, men det kan også være flere databaser involvert, henting av data fra andre tjenester, sammenstilling av ulik informasjon som skal returneres osv.
Dette er ofte hva som kalles forretningslogikk og noe som typisk legges inn i en service

### 3.1 Sortere brukere

Et veldig enkelt eksempel på logikk som gjerne legges i en service kan være å sortere data før den returneres.

**Oppgave**: Her skal du endre på `UserService` slik at listen som blir returnert fra serveren er sortert etter alder.

Hint: Det går å bruke den innebygde listeoperasjonen [sortedBy](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/sorted-by.html)
for å sortere en liste

## 4. Oppgave 4 - Exceptions

Når noe går galt er det fint å si ifra om det til klienten. Her er noen eksempler:
* 400 Bad Request - klienten har en ugyldig spørring, f.eks. har man kanskje ikke sendt med alle request params som er påkrevd
* 404 Not Found - man gjør en spørring mot en URL som ikke finnes
* 500 Internal Server Error - Noe uventet intreffet

### 4.1 Ressurs ikke funnet

**Oppgave**: Bruk `/user`-endepunktet fra [oppgave 1.2](#12-hent-en-spesifikk-bruker) og sørg for at endepunktet gir tilbake en respons
med HTTP status "404 Not found" når man spør etter en bruker som ikke finnes.

Hint: Det går an å kaste en feilmelding med ønsket HTTP status med følgende kode:
```
throw ResponseStatusException(HttpStatus.NOT_FOUND)
```

Hint: https://www.baeldung.com/spring-response-status-exception#1-generate-responsestatusexception

### 4.2 Beskrivelse av feilen (valgfritt)

**Oppgave**: Legg til en bekskrivelse når det kastes en exception

## Mulige oppgaver

* POST-kall der de må lage dataklassen for å deserialisere body-en som sendes inn via testen
* GET-kall der de må lage dataklassen for returtypen
* Noen enkle oppgaver for å vise bruk av headers