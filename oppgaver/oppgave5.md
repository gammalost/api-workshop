# Videre
Til nå har vi gått gjennom det vi oftes kommer over. Under følger litt diverse som bygger videre på det.

## 1 - Slett en bruker

Det å kunne legge til og hente brukere tar oss et stykke, men vi mangler enda mulighet for å slette en bruker.
For å slette en bruker skal vi gjøre et [DELETE](https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/DELETE)-kall.

**Oppgave**: Lag endepunktet `/user` som tar inn navnet på brukeren med path parameteret `name` og slett brukeren.

Eksempel på kall: `/user?name=Ola Nordmann`