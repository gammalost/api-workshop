# Videre
Til nå har vi gått gjennom det vi oftes kommer over. Under følger litt diverse som bygger videre på det.

## 1 - Slett en bruker

Det å kunne legge til og hente brukere tar oss et stykke, men vi mangler enda mulighet for å slette en bruker.
For å slette en bruker skal vi gjøre et [DELETE](https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/DELETE)-kall.

**Oppgave**: Lag endepunktet `/users/{userId}` som tar inn id-et på brukeren og sletter den.

Eksempel på kall: `/users/1`



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

## 3 - Bruk body ved post i stedet for query params

**Oppgave**: I oppgave 1.3 så lagde dere POST-endepunktet `/users` som tar inn `name` og `age` som request parametre.
Vanligvis sender man med en request body i POST-spørringer i stedet for request params. Lag et endepunkt `/postUser` 
som tar inn en dataklasse som innholder feltene `name` og `age` som request body.

## 4 - Beskrivelse av feilen

**Oppgave**: Legg til en bekskrivelse når det kastes en exception