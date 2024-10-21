# Oppgave 2 - HTTP statuser

På lik linje med at det finnes flere forskjellige HTTP-kall så finnes det også forskjellige HTTP-statuser man kan få tilbake.
Disse brukes for å gi mer informasjon om kallet, f.eks. om det gikk bra eller noe feilet underveis.

Vi skal se litt på noen av de mer vanlige, som er 2xx, 4xx og 5xx.

## 1 - 201 Created

2xx koder viser til at forespørselen til serveren er vellykket, dvs at serveren har mottatt, klart å behandle og svart forespørselen. Du kan lese mer om 2xx her: https://developer.mozilla.org/en-US/docs/Web/HTTP/Status#successful_responses 

**Oppgave**: Endre på POST-endepunktet `/users` så det gir tilbake 201 Created i stedet for 200 OK når man legger til en bruker.

<details>
<summary>Hvordan å returnere en spesifikk HTTP-status: 🕵️ 📜</summary>

```
return ResponseEntity.status(HttpStatus.CREATED).body(id)
```

</details>

🧪 Når du er ferdig, kjør testene i `Oppgave2` og sjekk at testen `Oppgave 2,1` fungerer.


## 2 - 404 Not found

* 4xx: Dette er feilkoder du får tilbake når det er noe feil i forespørselen. Det kan være at endepunktet ikke finnes, du forsøker å hente noe som ikke finnes, path-parametre mangler osv

_Les mer om 4xx her https://developer.mozilla.org/en-US/docs/Web/HTTP/Status#client_error_responses_



**Oppgave**: Bruk `/user`-endepunktet fra [oppgave 1.2](#12-hent-en-spesifikk-bruker) og sørg for at endepunktet gir tilbake en respons
med HTTP status "404 Not found" når man spør etter en bruker som ikke finnes.

<details>
<summary>Hvordan kaste exceptions: 🕵️ 📜</summary>
Det går an å kaste en exception med en spesifikk HTTP status på denne måten:

```
throw ResponseStatusException(HttpStatus.NOT_FOUND)
```

https://www.baeldung.com/spring-response-status-exception#1-generate-responsestatusexception

</details>

## 3 - 500 Internal server error

* 5xx: Disse kodene indikerer at serveren av en eller annen grunn ikke kan behandle forespørselen. Dette kan være fordi serveren ikke klarer å koble til en database, kall mot andre tjenester (API etc) feilet, at applikasjonen krasjet e.l.

_Les mer om 5xx her https://developer.mozilla.org/en-US/docs/Web/HTTP/Status#server_error_responses_

**Oppgave**: Lag endepunktet `/divide1000by/{tall}` som tar inn et tall og returnerer 1000 delt på tallet. Tving frem en exception du må håndtere ved å sende inn 0!

<details>
<summary>Hint 🕵️ 📜</summary>

---
Siden det å dele på 0 kaster en ArithmeticException må vi håndtere det med en try-catch. 
Hvis det feiler kan vi returnere en internal server error med:
```
throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR)
```
---
</details>
