# Rozwiązywanie problemów poprzez przeszukiwanie przestrzeni stanów

## Definicja problemu

Zdefiniowany jest zbiór n miast, każde miasto posiada współrzędne x i y, określające jego położenie na płaszczyźnie. Zakładamy, że każde dwa miasta są połączone drogą, której przebycie wiąże się z poniesieniem kosztu, oraz że koszt podróży po danej trasie w obie strony jest taka sama. Za koszt pokonania drogi uznajemy odległość pomiędzy miastami na płaszczyźnie. Tak zdefiniowany problem możemy przedstawić w postaci pełnego grafu ważonego, którego węzłami są miasta, a krawędziami drogi pomiędzy miastami.

Komiwojażer zaczyna podróż w dowolnym z miast. Jego celem jest odwiedzenie każdego z miast dokładnie jeden raz oraz powrót do miasta początkowego, przy możliwie najmniejszym koszcie.

Jako stan rozumiemy częściową lub całkowitą ścieżkę – w postaci uporządkowanej listy kolejno odwiedzanych miast wraz z przypisaną wartością funkcji oceny stanu. Przestrzenią dopuszczalnych rozwiązań jest dla nas drzewo, w którego korzeniu znajduje się miasto początkowe, a stany potomne tworzone są poprzez przejście jednej z dostępnych krawędzi grafu.

Należy rozwiązać problem

- metodą pełnego przeszukania przestrzeni stanów – w głąb oraz wszerz.

- metodą zachłanną

- metodą A*