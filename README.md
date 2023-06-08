# API-workshop

Backendtjenester kommer i mange former og fasonger, fra kjernebanksystemer som holder styr på kontoen din til webservere som håndterer forespørsler og returnerer informasjon.
Vi skal i denne workshopen se næmere på sistnevnte og sammen designe noen enkle API.

## Kom i gang

Vi skal bruke [Intellij](https://www.jetbrains.com/idea/) som IDE, [Gradle](https://gradle.org/) som byggverktøy (alternativ til Maven for de som er kjent med det) og Java 17. 
Dette skal allerede være installert på maskinene deres, men mulig det for noen må gjøres noe ekstra.

1. Klon repoet. I terminalen kjør denne kommandoen:
```
git clone git@github.com:gammalost/api-workshop.git
```
2. Åpne intellij og så åpne repoet du klonet

<img src="./images/intellij_open_project.png" width="45%" /> <img src="images/intellij_choose_repo.png" width="45%" />

3. Start applikasjonen og gå til `http://localhost:8080/hello` og sjekk at du får `Hello world!` tilbake  
   (Nb: dette kan ta litt tid mens gradle laster ned pakker for å kjøre applikasjonen)

<img src="images/intellij_start_application.png" width=300px />

4. Sjekk at du får kjørt testene til oppgave 1

<img src="images/intellij_find_task_1.png" max-width="45%" /> <img src="images/intellij_run_task_1_tests.png" max-width="45%" />


Et voilà! Om alt har gått knirkefritt så skal du nå være klar til å ta fatt på oppgavene! 🚀 Hvis ikke så rop ut, så kommer vi og hjelper! 🏃💨

## Okey... hva nå?

Det er satt opp tre filer, 
[UserController](src/main/kotlin/no/bekk/apiworkshop/apiworkshop/controller/UserController.kt), 
[UserService](src/main/kotlin/no/bekk/apiworkshop/apiworkshop/service/UserService.kt) og 
[UserRepository](src/main/kotlin/no/bekk/apiworkshop/apiworkshop/repository/UserRepository.kt). 
`UserController` og `UserService` er foreløpig tomme klasser - det er disse du skal legge til funksjonalitet i gjennom oppgavene. 
For å gjøre det litt lettere er `UserRepository` satt opp på forhånd med de funksjonene du skal trenge for å hente data fra databasen.

Enkel skisse over arkitekturen til backenden du skal jobbe med:

<img src="images/arkitektur.png" width="50% " />

For en liten recap av presentasjonen så er:
* Controller der vi definerer API-et (eksempel i [UserController](src/main/kotlin/no/bekk/apiworkshop/apiworkshop/controller/UserController.kt) med `helloWorld`-funksjonen)
* Service der vi legger logikk (eksempel i [UserService](src/main/kotlin/no/bekk/apiworkshop/apiworkshop/service/UserService.kt) med `helloWorld`-funksjonen))
* Repository der vi jobber mot persistent lagring (les: databaser) 

Databasen som er satt opp er en enkel in memory [H2](https://www.h2database.com/html/main.html)-database.
For testene er denne denne fyllt med litt innhold. Akkurat hva kan du se i [data.sql](src/test/resources/data.sql).

## 1. Oppgave 1 - HTTP-kall

Det finnes flere forskjellige http-forespørsler med forskjellige funksjoner. 
Vi skal nå se nærmere på de vanligste og lage noen enkle endepunkt for hver av de.

Beskrivelse av de ulike finner du her: https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods

### 1.1 Hent alle brukere

Vi har laget et database med brukere for denne workshopen. Innholdet i denne finner du i `data.sql`.
For denne oppgaven skal vi lage et endepunkt som returnerer alle brukerne fra databasen. Siden vi ønsker å hente informasjon så skal vi bruke et [GET](https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/GET)-kall.

**Oppgave**: Lag endepunktet `/users` i `UserController.kt` og hent data fra databasen.


<details>
<summary>Hint 🕵️ 📜</summary>

Gå til http://localhost:8080/users og sjekk hva du får tilbake. 
I stedet for å lime det inn i nettleseren kan man bruke Postman som er litt diggere.  [Les mer om Postman her](#postman).

</details>

🧪 Når du er ferdig, kjør testene i `Oppgave 1` og sjekk at testen `Oppgave 1,1` fungerer

### 1.2 Hent en spesifikk bruker

Ofte ønsker vi å ha mulighet til å hente en spesifikk bruker. Da må vi sende med informasjon om hvilken bruker vi ønsker.
For å spesifisere dette kan vi bruke en path parameter i url-en, eks. hente vekommende med ansattnummer 1000: `bekk.no/ansatte/1000`

**Oppgave**: Lag endepunktet `/user` og legg til mulighet for å spesifisere id-en til brukeren vi ønsker returnert med path parameteret `id`.

Eksempel på kall: `/user/1`

<details>
<summary>Hint 🕵️ 📜</summary>

Hvordan å håndtere path parametre: https://www.baeldung.com/spring-pathvariable
</details>

### 1.3 Legg til en bruker

Det å kun ha mulighet til å hente informasjon er en start, men det blir et lite dynamisk system. 
For å gjøre det mer brukbart skal du nå gjøre det mulig å legge til brukere.
For dette skal vi bruke et [POST](https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/POST)-kall.

**Oppgave**: Lag endepunktet `/user` som tar inn `name` og `age` som request parametre som skal brukes til å opprette brukeren.

Eksempel på kall: `/user?name=Ola Nordmann&age=20`

<details>
<summary>Hint 🕵️ 📜</summary>

Hvordan å håndtere request params: https://www.baeldung.com/spring-request-param
</details>


### 1.4 Slett en bruker (valgfritt)

Det å kunne legge til og hente brukere tar oss et stykke, men vi mangler enda mulighet for å slette en bruker.
For å slette en bruker skal vi gjøre et [DELETE](https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/DELETE)-kall.

**Oppgave**: Lag endepunktet `/user` som tar inn navnet på brukeren med path parameteret `name` og slett brukeren.

Eksempel på kall: `/user?name=Ola Nordmann`

### 1.5 Hent brukere filtrert på alder (valgfritt)

En generell tanke bak API-er er at de ikke returnerer mer data enn nødvendig.
For å gjøre dette er det praktisk at vi har mulighet å sende med informasjon å filtrere på.
Her kommer gjerne query parametre inn i bildet, eks: `bekk.no/ansatte/?skjorte=blå`.

**Oppgave**: Utvid endepunktet `/users` til å kunne ta inn `alderFra` og `alderTil` og bruk dette til å filtrere brukere.

Eksempel på kall: `/users/?alderFra=30,alderTil=50`

<details>
<summary>Hint 🕵️ 📜</summary>
Hvordan å gjøre parametre valgfrie: https://kotlinlang.org/docs/null-safety.html#nullable-types-and-non-null-types

Hvordan filtrere en liste: https://kotlinlang.org/docs/collection-filtering.html
</details>

## 2. Oppgave 2 - HTTP statuser

Som at det finnes flere forskjellige HTTP-kall så finnes det også forskjellige HTTP-statuser man kan få tilbake. 
Disse brukes for å si noe om et kall gikk bra eller noe feilet.
Listen over statuskodene og hva de alle betyr kan du lese mer om her: https://developer.mozilla.org/en-US/docs/Web/HTTP/Status

Vi skal fokusere på noen av de mer vanlige, som er 2xx, 4xx og 5xx.

### 2.1 201 Created

**Oppgave**: Endre på POST-endepunktet `/user` så det gir tilbake 201 Created i stedet for 200 OK når man legger til en bruker.

<details>
<summary>Hint 🕵️ 📜</summary>
Hvordan å returnere en spesifikk HTTP-status:

```
return ResponseEntity.status(HttpStatus.CREATED).build()
```
</details>

## 3. Oppgave 3 - Forretningslogikk

Til nå har vi sett på endepunktene til webserveren, men ofte ønsker vi også å gjøre noe mer som et resultat av kallene som kommer inn.
Dette kan være som vi i tidligere oppgaver har gjort med henting av data fra en database, men det kan også være flere databaser involvert, henting av data fra andre tjenester, sammenstilling av ulik informasjon som skal returneres osv.
Dette er ofte hva som kalles forretningslogikk og noe som typisk legges inn i en service

### 3.1 Sortere brukere

Et veldig enkelt eksempel på logikk som gjerne legges i en service kan være å sortere data før den returneres.

**Oppgave**: Her skal du endre på `UserService` slik at listen som blir returnert fra serveren er sortert etter alder.

<details>
<summary>Hint 🕵️ 📜</summary>
Hvordan å bruke den innebygde listeoperasjonen [sortedBy](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/sorted-by.html)
for å sortere en liste
</details>

## 4. Oppgave 4 - Exceptions

Når noe går galt er det fint å si ifra om det til klienten. Her er noen eksempler:
* 400 Bad Request - klienten har en ugyldig spørring, f.eks. har man kanskje ikke sendt med alle request params som er påkrevd
* 404 Not Found - man gjør en spørring mot en URL som ikke finnes
* 500 Internal Server Error - Noe uventet intreffet

### 4.1 Ressurs ikke funnet

**Oppgave**: Bruk `/user`-endepunktet fra [oppgave 1.2](#12-hent-en-spesifikk-bruker) og sørg for at endepunktet gir tilbake en respons
med HTTP status "404 Not found" når man spør etter en bruker som ikke finnes.

<details><summary>Hint 🕵️ 📜</summary>
Det går an å kaste en feilmelding med ønsket HTTP status med følgende kode:

```
throw ResponseStatusException(HttpStatus.NOT_FOUND)
```

https://www.baeldung.com/spring-response-status-exception#1-generate-responsestatusexception
</details>

### 4.2 Beskrivelse av feilen (valgfritt)

**Oppgave**: Legg til en bekskrivelse når det kastes en exception

## Mulige oppgaver

* POST-kall der de må lage dataklassen for å deserialisere body-en som sendes inn via testen
* GET-kall der de må lage dataklassen for returtypen
* Noen enkle oppgaver for å vise bruk av headers

## Postman

Postman er et verktøy man kan bruke for å lage spørringer mot API:er, her er noen fine ting med Postman:

* Responsen er formattert
* I nettleseren blir det altid sendt en GET-Request. I Postman er det mulig å sende andre typer requests, f.eks. Post.
* Man kan lagre spørringer
* Det er mulig å endre på parametrer i requesten: `body`, `query params`, `http-headers`, osv. 

Postman skal være installert på maskinene deres. Les mer om hvordan man kan få sendt en API-request med Postman i [dokumentasjonen](https://learning.postman.com/docs/getting-started/sending-the-first-request/#sending-an-api-request).

