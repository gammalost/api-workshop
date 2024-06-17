# Oppgave 2 - HTTP statuser

På lik linje med at det finnes flere forskjellige HTTP-kall så finnes det også forskjellige HTTP-statuser man kan få tilbake.
Disse brukes for å gi mer informasjon om kallet, som om gikk bra eller noe feilet.
Listen over statuskodene og hva de alle betyr kan du lese mer om her: https://developer.mozilla.org/en-US/docs/Web/HTTP/Status

Vi skal se litt på noen av de mer vanlige, som er 2xx, 4xx og 5xx.

## 1 - 201 Created

**Oppgave**: Endre på POST-endepunktet `/users` så det gir tilbake 201 Created i stedet for 200 OK når man legger til en bruker.

<details>
<summary>Hint 🕵️ 📜</summary>

Hvordan å returnere en spesifikk HTTP-status:

```
return ResponseEntity.status(HttpStatus.CREATED).body(id)
```

</details>

🧪 Når du er ferdig, kjør testene i `Oppgave2` og sjekk at testen `Oppgave 2,1` fungerer.


## 2 - 404 Not found

Når noe går galt er det fint å si ifra om det til klienten. Her er noen eksempler:
* 400 Bad Request - klienten har en ugyldig spørring, f.eks. har man kanskje ikke sendt med alle request params som er påkrevd
* 404 Not Found - man gjør en spørring mot en URL som ikke finnes
* 500 Internal Server Error - Noe feilet på server-siden

**Oppgave**: Bruk `/user`-endepunktet fra [oppgave 1.2](#12-hent-en-spesifikk-bruker) og sørg for at endepunktet gir tilbake en respons
med HTTP status "404 Not found" når man spør etter en bruker som ikke finnes.

<details>
<summary>Hint 🕵️ 📜</summary>

Det går an å kaste en exception med ønsket HTTP status med følgende kode:

```
throw ResponseStatusException(HttpStatus.NOT_FOUND)
```

https://www.baeldung.com/spring-response-status-exception#1-generate-responsestatusexception
</details>

## 3 - 500 Internal server error

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
