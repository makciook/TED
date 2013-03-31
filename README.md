TED
===

TED (<i>Task executing distributor</i>) to system, który wykonuje zadania. Szablonem zadania jest klasa implementująca interfejs java.lang.Runnable.

1. Budowanie i uruchomienie
---
Uruchomienie serwera nastepuje po wydaniu komendy:
<code>grails run-app</code>

Serwer tomcat zostaje uruchomiony lokalnie na porcie 8330. Staje sie dostepny pod adresem:
<code>http://localhost:8330</code>

1b. (Konfiguracja)
---
Domyslnie serwer przeznacza 4 watki na wykonywanie nowych zadan. Pozostale zostaja zakolejkowane i wykonane zaraz po zakonczeniu poprzedzajacych.
Parametr okreslajacy maksymalna ilosc zadan to <code>MAX_THREADS</code> w klasie <code>TaskService.groovy</code>. Domyslnie jest on ustawiony 
na wartosc 4, co jest wartoscia optymalna w przypadku uruchamiania zarowno zadan wymagajacych obliczeniowo, jak i tych prostych.
W zaleznosci od przeznaczenia serwera, wartosc <code>MAX_THREADS</code> moze zostac modyfikowana.

2. Interfejs
---
TED komunikuje sie ze swiatem poprzez interfejs webowy HTTP/JSON nasluchujacy na porcie 8330. Do wykonania mozliwe sa nastepujace zapytania HTTP:
* <code>GET /tasks/schedule?class=fully.qualified.class.Name</code> - zleca zadanie, odpowiada pusta trescia i statusami:
  - 200 (success), w przypadku zlecenia zadania
  - 202 (accepted), w przypadku nieudanej próby zlecenia zadania (brak klasy itp)
  - 400 (bad request), gdy popelniono blad skladniowy w zapytaniu
  Po wykonaniu komenty tworzone jest nowe zadanie. W przypadku, gdy 
* <code>GET /tasks/count-scheduled</code> - zwraca ilosc zadan w kolejce, odpowiada statusem 200
* <code>GET /tasks/count-running</code> - zwraca ilosc uruchomionych zadan, odpowiada statusem 200


3. Przyklady
---
Dostepne sa 3 klasy testowe:
* <code>codilime.ted.example.Itemize</code>
    Klasa wypisujaca w konsoli 5 jezykow programowania w odstepach 2-sekundowych.
* <code>codilime.ted.example.Fib</code>
    Stworzona do testowania wydajnosci serwera i ustawien wielowatkowosci. Oblicza algorytmem rekurencyjnym fib(40), co zajmuje jej od 1 do 2 minut.
* <code>codilime.ted.example.CountToN</code>
    Prosta klasa o dlugim czasie wykonania. Liczy od 0 do 19 z 2-sekkundowymi przerwami


4. Testy
---
Zaimplementowane testy spock wykonywane sa komenda:
<code>grails test-app :spock</code> i obejmuja:
- Odpowiedz na <code>count-scheduled</code>
- Odpowiedz na <code>count-running</code>
- Sprawdzenie ograniczen dla statusu zadania (0 - scheduled, 1 - running)




