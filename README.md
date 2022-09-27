# Rotable Remote Exercise - Terminplanung

## Intro

Vielen Dank dafür, dass du dir die Zeit nimmst unsere Test-User-Stories zu bearbeiten.  
Um die Aufgabe möglichst reibungslos bearbeiten zu können, bekommst du nachfolgend ein paar generelle Informationen zum Ablauf.  
[Hier (Szenario)](scenario_DE.md) findest du eine kurze Erläuterung des Arbeitskontexts – so kannst du dich bei der Bearbeitung noch etwas besser in die User-Stories hineinversetzen.

***Bitte beachte bei der Bearbeitung generell die folgenden Punkte:***

- Erstelle bitte ein Repository auf github/gitlab oÄ und schicke den Link zum Repository an David (david@rotable.at) sobald du die Aufgabe fertig bearbeitet hast.


- Wir haben die Aufgabe bewusst offen formuliert und auch bzgl. der verwendeten Technologien geben wir dir nur eine einzige Vorgabe: Die Programmiersprache, in der die Aufgabe gelöst werden soll ist ‚Java‘. Ansonsten kannst du komplett frei entscheiden, welche Technologien du für die Bearbeitung nutzen möchtest.


- Die Bearbeitungszeit beträgt grob 3-6 Stunden. Dies hängt vor allem davon ab wie viel Aufwände in deine eigenen Interpretationen der Aufgabe fließen. Wenn du nach 8 Stunden nicht mit allen Anforderungen fertig bist, ist es auch möglich einen Zwischenstand abzugeben mit einer kurzen Erklärung was dir Probleme bereitet hat.
Da wir die Aufgabe bewusst offen formuliert haben, kann es sein, dass du bei gewissen Punkten Annahmen treffen musst. Solltest du also im Zuge der Bearbeitung Anmerkungen, Diagramme oder Zeichnungen machen, kannst du David diese ebenfalls gerne zukommen lassen. Alternativ kannst du natürlich auch deine getroffenen Entscheidungen und deine Vorgehensweise im Follow-Up-Gespräch mit David nochmals besprechen.


- Generell gilt: Ziel der Aufgabe ist deinen Arbeitsstil und deine Vorgangsweise kennenzulernen. Es gibt keine richtige Lösung.


***Weitere Informationen:***
- Es muss keine UI/Weboberfläche erstellt werden.
- Es ist **NICHT** notwendig mehrere Microservices zu erstellen
- Eine H2 Datenbank ist bereits im Projekt konfiguriert, die Nutzung dieser (oder einer anderen Datenbank) ist jedoch optional. Es kann auch ein In-Memory Datastore genutzt werden, um die User Stories zu implementieren. 
- Es muss keine API-Dokumentation erstellt werden.
- Es gibt generell keine Conventions (z.B. zu Kommentaren oder Variablen/Feld/Methoden/Klassen-Benennungen).
- Bitte füge eine kurze README Datei hinzu, um etwaige Schritte zu erklären, die zur Ausführung des Programms notwendig sind.

 
Sobald du David die bearbeitete Aufgabe geschickt hast, werden wir uns bei dir bzgl. eines Follow-Up-Gesprächs melden.
Wenn du noch offene Fragen hast, kannst du dich natürlich gerne jederzeit melden, ansonsten: viel Spaß bei der Bearbeitung!  
Dein rotable-Team


LINK: [Szenario + User Stories](scenario_DE.md)


---


### Tech Setup

After starting, application should run on port http://localhost:8080.

Swagger UI (for visualizing  and testing the API) is reachable under http://localhost:8080/swagger-ui/index.html




#### Common Issues

- ***IntelliJ Error: Plugin 'org.springframework.boot:spring-boot-maven-plugin:' not found***  
From the Preferences in IntelliJ, navigate to "Build, Execution, Deployment > Build Tools > Maven", check the "Use plugin registry", and click "OK".
Then Invalidate Caches and Restart IntelliJ by going to "File > Invalidate Caches / Restart". The error should go away automatically.
