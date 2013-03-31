TED
===

TED (<i>Task executing distributor</i>) to system, który wykonuje zadania. Szablonem zadania jest klasa implementująca interfejs java.lang.Runnable.
Napisany w Groovy on Grails z wykorzystaniem testów Spock stanowi efektywną implementacje <i>Schedulera</i>

1. Budowanie i uruchomienie
---
Uruchomienie serwera następuje po wydaniu komendy:
<code>grails run-app</code>

Serwer tomcat zostaje uruchomiony lokalnie na porcie 8330. Staje się dostępny pod adresem:
<pre>http://localhost:8330</pre>

lub poprzez <a href="http://www.gnu.org/software/wget/">wget</a>, np:
<pre>wget http://localhost:8330/tasks/count-scheduled</pre>

1b. (Konfiguracja)
---
Domyślnie serwer przeznacza 4 wątki na wykonywanie nowych zadań. Pozostałe zostają zakolejkowane i wykonane zaraz po zakończeniu poprzedzających.
Parametr określający maksymalna ilość zadań to <code>MAX_THREADS</code> w klasie <code>TaskService.groovy</code>. Domyślnie jest on ustawiony 
na wartość 4, co jest wartością optymalna w przypadku uruchamiania zarówno zadań wymagających obliczeniowo, jak i tych prostych.
W zależności od przeznaczenia serwera, wartość <code>MAX_THREADS</code> może zostać modyfikowana.

2. Interfejs
---
TED komunikuje się ze światem poprzez interfejs webowy HTTP/JSON nasłuchujący na porcie 8330. Do wykonania możliwe są następujące zapytania HTTP:
* <code>GET /tasks/schedule?class=fully.qualified.class.Name</code> - zleca zadanie, odpowiada pustą treścią i statusami:
  - 200 (success), w przypadku zlecenia zadania
  - 202 (accepted), w przypadku nieudanej próby zlecenia zadania (brak klasy itp)
  - 400 (bad request), gdy popełniono bląd składniowy w zapytaniu
* <code>GET /tasks/count-scheduled</code> - zwraca ilość zadań w kolejce, odpowiada statusem 200
* <code>GET /tasks/count-running</code> - zwraca ilość uruchomionych zadań, odpowiada statusem 200


3. Przykłady
---
Dostępne są 3 klasy testowe:
* <code>codilime.ted.example.Itemize</code>
    Klasa wypisująca w konsoli 5 jeżyków programowania w odstępach 2-sekundowych.
* <code>codilime.ted.example.Fib</code>
    Stworzona do testowania wydajności serwera i ustawień wielowątkowości. Oblicza algorytmem rekurencyjnym fib(40), wykonanie zajmuje jej co najmniej kilkadziesiat sekund.
* <code>codilime.ted.example.CountToN</code>
    Prosta klasa o długim czasie wykonania. Liczy od 0 do 19 z 2-sekkundowymi przerwami


4. Testy
---
Zaimplementowane testy spock wykonywane są komenda:
<code>grails test-app :spock</code> i obejmuja:
- Odpowiedz na <code>count-scheduled</code>
- Odpowiedz na <code>count-running</code>
- Sprawdzenie ograniczeń dla statusu zadania (0 - scheduled, 1 - running)
