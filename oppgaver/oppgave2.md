# Oppgave 2 - HTTP statuser

På lik linje med at det finnes flere forskjellige HTTP-kall så finnes det også forskjellige HTTP-statuser man kan få tilbake.
Disse brukes for å gi mer informasjon om kallet, som om gikk bra eller noe feilet.
Listen over statuskodene og hva de alle betyr kan du lese mer om her: https://developer.mozilla.org/en-US/docs/Web/HTTP/Status

Vi skal se litt på noen av de mer vanlige, som er 2xx, 4xx og 5xx.

## 1 - 201 Created

**Oppgave**: Endre på POST-endepunktet `/user` så det gir tilbake 201 Created i stedet for 200 OK når man legger til en bruker.

<details>
<summary>Hint 🕵️ 📜</summary>

Hvordan å returnere en spesifikk HTTP-status:

```
return ResponseEntity.status(HttpStatus.CREATED).body(id)
```

</details>

## 2 - 404 Ressurs ikke funnet

Når noe går galt er det fint å si ifra om det til klienten. Her er noen eksempler:
* 400 Bad Request - klienten har en ugyldig spørring, f.eks. har man kanskje ikke sendt med alle request params som er påkrevd
* 404 Not Found - man gjør en spørring mot en URL som ikke finnes
* 500 Internal Server Error - Noe feilet på server-siden

**Oppgave**: Bruk `/user`-endepunktet fra [oppgave 1.2](#12-hent-en-spesifikk-bruker) og sørg for at endepunktet gir tilbake en respons
med HTTP status "404 Not found" når man spør etter en bruker som ikke finnes.

<details>
<summary>Hint 🕵️ 📜</summary>

Det går an å kaste en feilmelding med ønsket HTTP status med følgende kode:

```
throw ResponseStatusException(HttpStatus.NOT_FOUND)
```

https://www.baeldung.com/spring-response-status-exception#1-generate-responsestatusexception
</details>
