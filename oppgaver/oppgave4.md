# Videre
Til nå har vi gått gjennom det vi oftes kommer over. Under følger litt diverse som bygger videre på det.

## 1 - Slett en bruker

Det å kunne legge til og hente brukere tar oss et stykke, men vi mangler enda mulighet for å slette en bruker.
For å slette en bruker skal vi gjøre et [DELETE](https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/DELETE)-kall.

**Oppgave**: Lag endepunktet `/users/{userId}` som tar inn id-et på brukeren og sletter den.

🧪 Når du er ferdig, kjør testene i `Oppgave4` og sjekk at testen `Oppgave 4,1` fungerer.


## 2 - Returner nytt objekt med epost

Det er ofte vi ønsker å ha en egen returtype, separert fra den interne datastrukturen i applikasjonen vår.
I denne oppgaven skal vi lage en egen dataklasse som vi skal returnere til klienten.

**Oppgave**: Lag endepunktet `/usersDetailed` som returnerer det samme som `User`-klassen, men i tillegg et epost-felt som skal være på formen `Fornavn.Etternavn@bekk.no`.

Eksempel på returnert objekt:
```json
[
  {
    "id": 1,
    "name": "Espen Askeladd",
    "age": 20,
    "email": "Espen.Askeladd@bekk.no"
  }
]
```

## 3 - Beskrivelse av feilen

**Oppgave**: Legg til en bekskrivelse når det kastes en exception
