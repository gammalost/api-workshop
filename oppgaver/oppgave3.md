# Oppgave 3 - Exceptions

Når noe går galt er det fint å si ifra om det til klienten. Her er noen eksempler:
* 400 Bad Request - klienten har en ugyldig spørring, f.eks. har man kanskje ikke sendt med alle request params som er påkrevd
* 404 Not Found - man gjør en spørring mot en URL som ikke finnes
* 500 Internal Server Error - Noe uventet intreffet

## 1 - Ressurs ikke funnet

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

## 2 - Beskrivelse av feilen (valgfritt)

**Oppgave**: Legg til en bekskrivelse når det kastes en exception