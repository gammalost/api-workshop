# Oppgave 2 - HTTP statuser

Som at det finnes flere forskjellige HTTP-kall så finnes det også forskjellige HTTP-statuser man kan få tilbake.
Disse brukes for å si noe om et kall gikk bra eller noe feilet.
Listen over statuskodene og hva de alle betyr kan du lese mer om her: https://developer.mozilla.org/en-US/docs/Web/HTTP/Status

Vi skal fokusere på noen av de mer vanlige, som er 2xx, 4xx og 5xx.

## 1 - 201 Created

**Oppgave**: Endre på POST-endepunktet `/user` så det gir tilbake 201 Created i stedet for 200 OK når man legger til en bruker.

<details>
<summary>Hint 🕵️ 📜</summary>

Hvordan å returnere en spesifikk HTTP-status:

```
return ResponseEntity.status(HttpStatus.CREATED).body(id)
```

</details>