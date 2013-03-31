TED
===

Task executing distributor to system, który wykonuje zadania. Szablonem zadania jest klasa implementująca interfejs java.lang.Runnable.

1. Budowanie i uruchomienie
---

Uruchomienie serwera nastepuje po wydaniu komendy:
  : grails run-app

Mozliwe jest takze wykonanie zaimplementowanych testow spock:
  : grails test-app :spock
  
Serwer tomcat zostaje uruchomiony lokalnie na porcie 8330. Dostep pod adresem:
  : http://localhost:8330/tasks/...

2. Interfejs
---

TED komunikuje sie ze swiatem poprzez interfejs webowy HTTP/JSON nasluchujacy na porcie 8330. Do wykonania mozliwe sa nastepujace zapytania HTTP:
* GET /tasks/schedule?class=fully.qualified.class.Name - zleca zadanie, odpowiada pusta trescia i statusami:
  - 200 (success), w przypadku zlecenia zadania
  - 202 (accepted), w przypadku nieudanej próby zlecenia zadania (brak klasy itp)
  - 400 (bad request), gdy popelniono blad skladniowy w zapytaniu
* GET /tasks/count-scheduled - zwraca ilosc zadan w kolejce, odpowiada statusem 200
* GET /tasks/count-running - zwraca ilosc uruchomionych zadan, odpowiada statusem 200

