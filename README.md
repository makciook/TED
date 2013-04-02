TED
===

<<<<<<< HEAD
TED (<i>Task executing distributor</i>) to system, który wykonuje zadania. Szablonem zadania jest klasa implementująca interfejs java.lang.Runnable.
Napisany w Groovy on Grails z wykorzystaniem testów Spock stanowi efektywną implementacje <i>Schedulera</i>.

1. Budowanie i uruchomienie
---
Uruchomienie serwera następuje po wydaniu komendy:
<code>grails run-app</code>

Serwer tomcat zostaje uruchomiony lokalnie na porcie 8330. Staje się dostępny pod adresem:
<pre>http://localhost:8330</pre>

lub poprzez <a href="http://www.gnu.org/software/wget/">wget</a>, np:
=======
TED (Task executing distributor) to system, który wykonuje zadania. Szablonem zadania jest klasa implementująca interfejs java.lang.Runnable. Napisany w Groovy on Grails z wykorzystaniem testow Spock stanowi efektywna implemetacje Schedulera

1. Budowanie i uruchomienie
---
Uruchomienie serwera nastepuje po wydaniu komendy: <code>gradle grails-run-app</code> (grails run-app)

Wymagane jest posiadanie zainstalowanego srodowiska budowania Gradle w wersji 1.4 lub Grails 2.2.1.

Serwer tomcat zostaje uruchomiony lokalnie na porcie 8330. Staje sie dostepny pod adresem:
<pre>http://localhost:8330</pre>
lub poprzez wget, np:
>>>>>>> 67efca988f7ea719026e18f433548cb8a6baa655
<pre>wget http://localhost:8330/tasks/count-scheduled</pre>

1b. (Konfiguracja)
---
<<<<<<< HEAD
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
  - 400 (bad request), gdy popełniono błąd składniowy w zapytaniu
* <code>GET /tasks/count-scheduled</code> - zwraca ilość zadań w kolejce, odpowiada statusem 200
* <code>GET /tasks/count-running</code> - zwraca ilość uruchomionych zadań, odpowiada statusem 200


3. Przykłady
---
Dostępne są 3 klasy testowe:
* <code>codilime.ted.example.Itemize</code>
    Klasa wypisująca w konsoli 5 jeżyków programowania w odstępach 2-sekundowych.
* <code>codilime.ted.example.Fib</code>
    Stworzona do testowania wydajności serwera i ustawień wielowątkowości. Oblicza algorytmem rekurencyjnym fib(40), wykonanie zajmuje jej co najmniej kilkadziesiąt sekund.
* <code>codilime.ted.example.CountToN</code>
    Prosta klasa o długim czasie wykonania. Liczy od 0 do 19 z 2-sekkundowymi przerwami


4. Testy
---
Zaimplementowane testy spock wykonywane są komendą:
<code>grails test-app :spock</code> (w szczególności: integration:spock lub unit:spock) i obejmują:
* odpowiedzi serwera na zapytanie <code>count-scheduled</code>, <code>count-running</code> oraz <code>schedule</code> dla istniejącej i nieistniejącej klasy.
* przypadek braku parametru "class" dla zapytania <code>schedule</code>
* klasa Task sprawdzana jest pod katem poprawności obliczania uruchomionych i zaplanowanych tasków
* testowane są także ograniczenia statusu (<code>state</code>) Task-ów
* use case dodania 5 nowych Task-ów dla <code>MAX_THREADS = 4</code> - 4 uruchomione, 1 czeka
* use case dodania kolejnego Task-u - 4 uruchomione, 2 czekaja
=======
Domyslnie serwer przeznacza 4 watki na wykonywanie nowych zadan. Pozostale zostaja zakolejkowane i wykonane zaraz po zakonczeniu poprzedzajacych. Parametr okreslajacy maksymalna ilosc zadan to <code>MAX_THREADS</code>
w klasie <code>TaskService.groovy</code>. Domyslnie jest on ustawiony na wartosc 4, co jest wartoscia optymalna w przypadku uruchamiania zarowno zadan wymagajacych obliczeniowo, jak i tych prostych. W zaleznosci od przeznaczenia serwera, wartosc <code>MAX_THREADS</code> moze zostac modyfikowana.

2. Interfejs
---
TED komunikuje sie ze swiatem poprzez interfejs webowy HTTP/JSON nasluchujacy na porcie 8330. Do wykonania mozliwe sa nastepujace zapytania HTTP:
*  <code>GET /tasks/schedule?class=fully.qualified.class.Name</code> - zleca zadanie, odpowiada pusta trescia i statusami:
	- 200 (success), w przypadku zlecenia zadania
	- 202 (accepted), w przypadku nieudanej próby zlecenia zadania (brak klasy itp)
	- 400 (bad request), gdy popelniono blad skladniowy w zapytaniu Po wykonaniu komenty tworzone jest nowe zadanie. W przypadku, gdy
*  <code>GET /tasks/count-scheduled</code> - zwraca ilosc zadan w kolejce, odpowiada statusem 200
*  <code>GET /tasks/count-running</code> - zwraca ilosc uruchomionych zadan, odpowiada statusem 200

3. Przyklady
---
Dostepne sa 3 klasy testowe:
*	<code>codilime.ted.example.Itemize</code> Klasa wypisujaca w konsoli 5 jezykow programowania w odstepach 2-sekundowych.
*	<code>codilime.ted.example.Fib</code> Stworzona do testowania wydajnosci serwera i ustawien wielowatkowosci. Oblicza algorytmem rekurencyjnym fib(40), co zajmuje jej od 1 do 2 minut.
*	<code>codilime.ted.example.CountToN</code> Prosta klasa o dlugim czasie wykonania. Liczy od 0 do 19 z 2-sekundowymi przerwami

4. Testy
---
Zaimplementowane testy spock wykonywane sa komenda: <code>gradle grails-test-app -Pargs=":spock"</code> (grails test-app :spock) i obejmuja:
* Sprawdzanie poprawnosci stanow dodawanych zadan (Task) w Domenie
* Weryfikacja odpowiedzi serwera na kazde z zapytan omowionych w <a href="https://github.com/makciook/TED/blob/master/README.md#2-interfejs">punkcie 2</a>.
* Use case dodania 5 Task-ow - 4 uruchomione, 1 zaplanowany
* Dodanie nowego Task-u - 4 uruchomione, 2 zaplanowane.
>>>>>>> 67efca988f7ea719026e18f433548cb8a6baa655
