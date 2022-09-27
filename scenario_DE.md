# Szenario

Für ein bereits in Verwendung stehendes Tool zur Personalverwaltung soll eine Erweiterung zur Terminplanung entwickelt
werden.
Alle schon bestehenden Komponenten sind als autonome Microservices entwickelt.  
Auch neue Komponenten sind als komplett eigenständige Services zu entwickeln.

Eine UI bzw. zugehörige Webanwendung muss für den Service nicht entwickelt werden, dies wird von einem anderen Team
durchgeführt.
Somit handelt es sich um ein reines Backend Service der eine API für eine Webanwendung oder Desktop Anwendung
bereitstellt.
Es soll sich um eine REST Schnittstelle handeln, wie diese aussieht und welche Technologien eingesetzt werden ist dem
Entwickler jedoch freigestellt.  

Um zukünftige Erweiterungen durch andere Entwickler zu vereinfachen ist weiters JAVA als zu verwendende
Programmiersprache firmenintern vorgegeben.  
Generell herrscht etwas Zeitdruck im Projekt, dennoch ist eine gute Qualität besonders wichtig, da es bei einem Fehler
oder Ausfall eines Service zu Verzögerungen der internen Prozesse kommt.

In dem Projekt wird ein agiles Vorgehen verfolgt. Der Product Owner erstellt in JIRA ein EPIC mit dem Namen
„Terminplanungsservice“ und erstellt mehrere User Stories, die er in Refinements zusammen mit dem Scrum Team bespricht.
Das Team verständigt sich auf ein Sprintziel und in der Absprache mit dem Team sind von dir folgende zwei User Stories
zu erledigen:

### User-Story 1 – Bereitstellung einer API zur Verwaltung von Nutzern

Als Entwickler möchte ich die Möglichkeit haben, mittels der bereitgestellten API des Terminplanungsservice, Benutzer
anzulegen, sie zu ändern und zu löschen.  

Ein Nutzer besteht dabei mindestens aus einem Identifier und einem Namen.  
In meiner Anwendung soll es zudem möglich sein, einen bestimmten Benutzer mit allen seiner Nutzerdaten abzufragen. 
Zudem ist es weiterhin notwendig auch alle Benutzer auf einmal abzufragen. Auch bei dieser Abfrage sollen alle Nutzerdaten
mitgeschickt werden.  

Wenn ein Benutzer gelöscht wird, müssen zusätzlich alle Termine, in den der Nutzer eingetragen ist, angepasst werden und
der Nutzer aus diesen Terminen entfernt werden.

### User-Story 2 - Terminplanung
Als Benutzer möchte ich Termine anlegen, ändern und löschen können.

Ein Termin besteht dabei immer aus mindestens einem Nutzer, einem Start- sowie einen Endzeitpunkt und einem Titel.  
Zudem möchte ich alle Termine mit allen Nutzern sehen sowie auch nur die Termine eines einzelnen Nutzers abrufen
können.  
Bei der Erstellung eines Termins muss sichergestellt werden, dass der Termin zu keinen Überlappungen führt.  
Ein Nutzer kann immer nur einen Termin zu einem Zeitpunkt haben.  

Überschneidende Termine sind nicht erlaubt und müssen von der API mit einem entsprechenden Fehler abgelehnt werden.
Dabei soll ersichtlich sein in welchem Zeitraum und mit welchen Terminen es zu Überschneidungen kommt.


***Beispiel:***  
Ich möchte einen neuen Termin von ***10.01.2022*** bis ***31.01.2022*** anlegen.  
Da ich aber bereits einen Termin namens 'Urlaub' von ***01.01.2022*** bis ***15.01.2022*** erstellt habe bekomme ich eine Fehlermeldung:
> "Es besteht eine Überschneidung mit 'Urlaub' von 10.01.2022 bis 15.01.2022"
