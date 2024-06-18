# Oppgave 3 - Forretningslogikk

Til nå har vi sett på endepunktene til webserveren, men ofte ønsker vi også å gjøre noe mer som et resultat av kallene som kommer inn.
Dette kan være som vi i tidligere oppgaver har gjort med henting av data fra en database, men det kan også være flere databaser involvert, henting av data fra andre tjenester, sammenstilling av ulik informasjon som skal returneres osv.
Dette er ofte hva som kalles forretningslogikk og noe som typisk legges inn i en service

## 1 - Sortere brukere

Et veldig enkelt eksempel på logikk som gjerne legges i en service kan være å sortere data før den returneres.

**Oppgave**: Her skal du endre på `UserService` slik at listen som blir returnert fra serveren er sortert etter alder.

<details>
<summary>Hint 🕵️ 📜</summary>

Bruk [sortedBy](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/sorted-by.html)
for å sortere en liste.
</details>

🧪 Når du er ferdig, kjør testene i `Oppgave3` og sjekk at testen `Oppgave 3,1` fungerer.


## 2 - Hent brukere filtrert på alder

En generell tanke bak API-er er at de ikke returnerer mer data enn nødvendig.
For å gjøre dette er det praktisk at vi har mulighet å sende med informasjon å filtrere på.
Her kommer gjerne query parametre inn i bildet, eks: `bekk.no/ansatte?skjorte=blå`.

**Oppgave**: Utvid endepunktet `/users` til å kunne ta inn `alderFra` og `alderTil` og bruk dette til å filtrere brukere.
Eksempelvis burde `alderFra=30` og `alderTil=40` gi tilbake alle brukere som er fra og med 30 og til og med 40 år gamle.

Eksempel på kall: `/users?alderFra=30&alderTil=50`

<details>
<summary>Hint 🕵️ 📜</summary>

Eksempel på hvordan man kan bruke query parametetre: https://www.baeldung.com/spring-request-param

Håndtere variabler som kan være null: https://kotlinlang.org/docs/null-safety.html#nullable-types-and-non-null-types

Eksempler på hvordan å filtrere en liste: https://kotlinlang.org/docs/collection-filtering.html
</details>
