# Progetto esame Programmazione a Oggetti, Gennaio2022.

<a name="introduzione"></a>
## Introduzione
Questo programma ha come scopo quello di offrire all'utente (tramite chiamate alle API Current di [OpenWeather](https://openweathermap.org/)) la possibilità di ottenere previsioni meteo istantanee complete relative a una città e di poter calcolare delle statistiche relative a visibilità e umidità di una città, potendo anche scegliere il periodo di tempo su cui calcolarle. Le statistiche su umidità e visibilità vengono calcolate prendendo i valori necessari da dei file presenti nella cartella [dataExamples](https://github.com/LudovicoGo/Progetto_OOP_ESAME/tree/master/ProgettoEsame/src/main/resources/dataExamples) (attualmente sono presenti dei file di esempio, ma tali file possono anche essere generati usando questo programma).

## INDICE:
* [Introduzione](#introduzione)
* [Installazione e configurazione del programma](#installEConf)
* [Rotte](#rotte)
* [Eccezioni](#eccezioni)
* [Test effettuati](#test)
* [Lettura API OpenWeather](#API)
* [Documenti, programmi e risorse utilizzati](#programmidocumenti)
* [Autori](#autori)

<a name="installEConf"></a>
## Installazione e Configurazione
Il programma può essere installato clonandolo da questa directory tramite prompt dei comandi, usando [GitHub Desktop](https://desktop.github.com/) o direttamente da IDE per esempio da [Intellij](https://blog.jetbrains.com/idea/2020/10/clone-a-project-from-github/).

Nel programma è già presente una  ``` API Key ```  messa a vostra disposizione, è possibile sostituirla con una propria andando a modificare tale voce nella classe [WeatherServiceImpl](https://github.com/LudovicoGo/Progetto_OOP_ESAME/blob/master/ProgettoEsame/src/main/java/com/progetto/ProgettoEsame/service/WeatherServiceImpl.java).

Per avviare il programma è sufficiente runnare la classe [ProgettoEsameApplication](https://github.com/LudovicoGo/Progetto_OOP_ESAME/blob/master/ProgettoEsame/src/main/java/com/progetto/ProgettoEsame/ProgettoEsameApplication.java).

<a name="rotte"></a>
## Rotte
Riferendosi al seguente indirizzo:

```
localhost:8080
```


L'utente ha a disposizione le seguenti rotte:
 
| Rotta             | Tipo rotta |        Descrizione                                           
|-------------------|------------|--------------------------------------------------------------
| [/weather](#_1)          |     GET    | Restituisce un JSONObject contenente le previsioni meteo istantanee (anche umidità e visibilità) della città richiesta.
| [/scheduledWeather](#_2) |     GET    | Salva su file JSON le previsioni del meteo complete di una città in modo ciclico, una volta all'ora, in un certo periodo di tempo indicato dall'utente (TimeSlot, Daily, Weekly, ChosenDay).
| [/visibility](#_3)       |     GET    | Calcola media e varianza della visibilità, relative ad un dato periodi di tempo, di una città prendendone i valori da un file JSON.
| [/humidity](#_4)         |     GET    | Calcola media e varianza della umidità, relative ad un dato periodi di tempo, di una città prendendone i valori da un file JSON.

### Spiegazione rotte
   <a name="_1"></a>
  - **<b>Utilizzo rotta /weather</b>**:

    Questa rotta prende in input il nome di una città e restituisce un JSONObject con dentro le previsioni meteo istantanee complete (comprese visibilità e umidità), si può inserire una città qualsiasi, nel caso si dimentichi di specificare il nome della città verrà restituito un errore. Si utilizza cosi:
  
      ```
      localhost:8080/weather?cityName={NomeCittà}
      ```
      (mettere il nome della città richiesta al posto di "NomeCittà" e rimuovere le parentesi graffe)
      
     <b>Esempio risposta:</b>
      
      ![MilanWeather](https://user-images.githubusercontent.com/91212120/149629162-299044bb-7ce9-4c3b-a7bf-9d0458426479.png)
      
  <a name="_2"></a>  
 - **<b>Utilizzo rotta /scheduledWeather</b>**:
 
    Questa rotta prende in input il nome di una città, un periodo di tempo, l'inizio e la fine di quel periodo di tempo e salva ciclicamente ogni ora su un file JSON le previsioni meteo istantanee complete (comprese visibilità e umidità), si può inserire una città qualsiasi. I periodi di tempo disponibili sono TimeSlot, Daily, ChosenDay, Weekly. Le date vanno inserite seguendo il formato ddMMyyyyHHmmss.
      I file avranno come nome "nome città + periodo di tempo + WeatherArray.json" e verranno salvati all'interno della cartella del progetto nel seguente percorso:  
      
      ```
      "src/main/resources/saved/NomeFile.json"
      ```   
     <b>Esempio risposta:</b>
     
      ![rispostascheduled](https://user-images.githubusercontent.com/91212120/150353839-31b60925-7740-4226-bc75-2f0940d62584.png)

           
      Questa rotta si può usare in diversi modi a seconda del periodo di tempo scelto:
      
      - **<b>Daily</b>**: 
         Richiede come input necessari il nome della città e il periodo di tempo che deve essere scelto è "Daily", salva su un file le previsioni del meteo a partire da quando si invia la richiesta fino alla fine del giorno corrente, si può scrivere cosi:
         
         ```
         localhost:8080/scheduledWeather?cityName={NomeCittà}&period=Daily
         ```
         (mettere il nome della città richiesta al posto di "NomeCittà" e rimuovere le parentesi graffe)
         
         
      - **<b>TimeSlot</b>**: 
         Richiede come input il nome della città, va scelto il periodo di tempo "TimeSlot", va fornita la data (e ora) del momento di inizio e della fine della fascia oraria durante la quale si vuole che avvenga il salvataggio delle previsioni. Salva su un file le previsioni del meteo, ogni ora, della <b>fascia oraria</b> definita inserendo l'ora d'inizio e quella di fine. Va usata cosi:
         
         ```
         localhost:8080/scheduledWeather?cityName={NomeCittà}&period=TimeSlot&initialParam={DataInizio}&finalParam={DataFine}
         ```
         (inserire i parametri e rimuovere le parentesi graffe)
         
         
      - **<b>ChosenDay</b>**: 
         Richiede come input necessari il nome della città e la data del giorno scelto (a qualsiasi ora e sempre usando il formato ddMMyyyyHHmmss), va scelto il periodo di tempo "ChosenDay". Salva su un file le previsioni del meteo di un <b>giorno a scelta diverso da quello corrente</b>, ogni ora, a partire dalle ore 00:00 del giorno indicato fino alle ore 24:00 dello stesso. Va usata cosi:
         
         ```
         localhost:8080/scheduledWeather?cityName={NomeCittà}&period=ChosenDay&initialParam={DataGiornoScelto}
         ```
         (inserire i parametri e rimuovere le parentesi graffe)
         
            
      - **<b>Weekly</b>**: 
         Richiede come input necessari il nome della città, va scelto il periodo di tempo "Weekly". Salva su un file le previsioni del meteo <b>della settimana</b>, ogni ora, a partire dall'ora corrente fino a 7 giorni dopo. Va usata cosi:
         
         ```
         localhost:8080/scheduledWeather?cityName={NomeCittà}&period=Weekly
         ```
         (inserire i parametri e rimuovere le parentesi graffe)
         
  <a name="_3"></a>          
 - **<b>Utilizzo rotta /visibility</b>**:
 
    Questa rotta prende in input il nome di una città e il nome di un periodo di tempo (TimeSlot, Weekly, ChosenDay, Daily), si può inserire una città tra quelle presenti nel database di esempi (ovvero: Milan, Valencia, London, Paris).
    Chiamando questa rotta il programma prende il file su cui sono salvati i dati del meteo della città scelta nel periodo scelto, ne estrapola i valori della visibilità e li usa per calcolare media e varianza di quest'ultima.    
     Va usata cosi:
     
     ```
     localhost:8080/visibility?cityName={NomeCittà}&period={PeriodoDiTempo}
     ```
     <b>Esempio risposta:</b>
      
    ![visibilityMilan](https://user-images.githubusercontent.com/91212120/149629229-1c6ead11-9cb3-4524-a97f-5fd6e0efe0aa.png)
    
      I file devono avere come nome "nome città + periodo + WeatherArray.json", possono essere generati tramite l'uso della rotta /scheduledWeather (vedi sopra) e sono salvati all'interno della cartella del progetto nel seguente percorso:  
      
      ```
      "src/main/resources/dataExamples/NomeFile.json"
      ```


              
  <a name="_4"></a>           
 - **<b>Utilizzo rotta /humidity</b>**:
 
    Questa rotta prende in input il nome di una città e il nome di un periodo di tempo (TimeSlot, Weekly, ChosenDay, Daily), si può inserire una città tra quelle presenti nel database di esempi (ovvero: Milan, Valencia, London, Paris).
    Chiamando questa rotta il programma prende il file su cui sono salvati i dati del meteo della città scelta nel periodo scelto, ne estrapola i valori dell'umidità e li usa per calcolare media e varianza di quest'ultima.
     Va usata cosi:
     
     ```
     localhost:8080/humidity?cityName={NomeCittà}&period={PeriodoDiTempo}
     ```      
     <b>Esempio risposta:</b>
      
   ![humidityMilan](https://user-images.githubusercontent.com/91212120/149629357-20080bf4-1f30-4d56-a338-aa6312036053.png)

    
      I file devono avere come nome "nome città + periodo + WeatherArray.json", possono essere generati tramite l'uso della rotta /scheduledWeather e sono salvati all'interno della cartella del progetto nel seguente percorso:  
      
      ```
      "src/main/resources/dataExamples/NomeFile.json"
      ```



<a name="eccezioni"></a>
## Eccezioni

 - **<b>CantFindDataException</b>**:

   Viene lanciata quando non si trova il file relativo alle previsioni richieste, per esempio richiedere le statistiche della visibilità di una città non presente nel database porta a questo errore.
   Questa eccezione viene lanciata nel [controller](https://github.com/LudovicoGo/Progetto_OOP_ESAME/blob/master/ProgettoEsame/src/main/java/com/progetto/ProgettoEsame/controller/WeatherController.java) dai metodi getHumidityStats e getVisibilityStats, restituisce il seguente messaggio di errore:
   
   ```ERRORE! Attualmente conosco la visibilità / l'umidità solo delle seguenti città: Milan, Valencia, London, Paris```
    
- **<b>CityException</b>**:

  Viene lanciata quando si dimentica di inserire il nome della città di cui si vogliono ottenere / salvare su file le previsioni.
  Viene lanciata dal [controller](https://github.com/LudovicoGo/Progetto_OOP_ESAME/blob/master/ProgettoEsame/src/main/java/com/progetto/ProgettoEsame/controller/WeatherController.java) nei metodi getWeather e saveScheduledWeather, restituisce il seguente messaggio di errore:
  
   ```ERRORE! Non hai inserito il nome della città, riprova!```
  
- **<b>TimeSlotException</b>**:

  Viene lanciata quando si provano a salvare su file delle previsioni usando un periodo di tempo non ammesso dal programma o si dimentica di inserire una data richiesta per l'esecuzione dell'operazione.
  Viene lanciata nel [controller](https://github.com/LudovicoGo/Progetto_OOP_ESAME/blob/master/ProgettoEsame/src/main/java/com/progetto/ProgettoEsame/controller/WeatherController.java) dal metodo saveScheduledWeather, restituisce il seguente messaggio di errore:
  
   ```Hai commesso un ERRORE nell'inserimento delle date/ore```
 
<a name="test"></a>
## Test effettuati   
- **<b>ServiceTest</b>**: sono test effettuati per verificare il corretto funzionamento dei metodi della classe <b>[WeatherServiceImpl](https://github.com/LudovicoGo/Progetto_OOP_ESAME/blob/master/ProgettoEsame/src/main/java/com/progetto/ProgettoEsame/service/WeatherServiceImpl.java)</b>, in particolare sono:
   - <b>shouldFindThatCity</b>:
        Test che va a verificare che la città di cui si ottengono le previsioni tramite il metodo <b>getJSONWeather</b> sia effettivamente quella richiesta dall'utente.
   - <b>shouldConvertDate</b>:
        Test che va a verificare il corretto funzionamento del metodo <b>DataConverter</b> attraverso la comparazione di una data già convertita e una che convertita dal metodo DataConverter stesso.
 
 - **<b>StatTest</b>**: sono test effettuati per verificare il corretto funzionamento dei metodi della classe <b>[Statistics](https://github.com/LudovicoGo/Progetto_OOP_ESAME/blob/master/ProgettoEsame/src/main/java/com/progetto/ProgettoEsame/filterAndStats/Statistics.java)</b>, in particolare sono:
    - <b>shouldCalculateAverageAndVariance</b>: 
       Test che va a verificare il corretto funzionamento dei metodi <b>Average</b> e <b>Variance</b>, cioè se calcolano in modo corretto media e varianza di una serie di valori.
 
    - <b>shouldFindMaxMinValue</b>:  
       Test che va a verificare il corretto funzionamento dei metodi <b>MaxValue</b> e <b>MinValue</b>, cioè vede se riescono ad estrarre il valore minimo e massimo contenuti in un <b>Vector di Long</b> in modo corretto.


<a name="API"></a>
## Aiuto per la lettura delle API di OpenWeather  
<b>Ecco una legenda a cui fare riferimento per la comprensione dei parametri  per noi più importanti</b>:
   * ```coord``` 
     - ```coord.lon``` Latitudine della città.
     - ```coord.lat``` Longitudine della città.
        
* ```weather``` 
     - ```weather.id``` id delle condizioni meteo.
     - ```weather.main``` Condizione meteo.
     - ```weather.description``` Descrizione più accurata delle condizioni meteo.
 
* ```main```
     - ```main.temp``` Temperatura attuale. .
     - ```main.feels_like``` Temperatura percepita.
     - ```main.pressure``` Pressione atmosferica.
     - ```main.humidity``` Umidità.
     - ```main.temp_min``` Temperatura minima.
     - ```main.temp_max``` Temperatura massima.
     - ```main.sea_level``` Pressione a livello del mare.
     - ```main.grnd_level``` Pressione a livello del suolo.
 
 * ```wind```
     - ```wind.speed``` Velocità del vento.
     - ```wind.deg``` Direzione del vento.
 
 * ```clouds```
     - ```clouds.all``` Percentuale di nuvole.
 
 * ```timezone``` Fuso orario.
 
 * ``` dt ``` Data e ora in unix di quando sono state fatte le previsioni.
 
 * ```sys```
      - ```sys.country``` Nazione.
      - ```sys.sunrise``` Ora alba in unix.
      - ```sys.sunset``` Ora tramonto in unix.
 * ```id``` ID della città.
 * ```name``` Nome della città.
       
       
      
<a name="programmidocumenti"></a>
## Documenti, pogrammi e risorse utilizzati

**<b>Programmi e risorse utilizzati</b>**:

 - <b>[Intellij](https://www.jetbrains.com/idea/)</b>: è l'IDE sul quale abbiamo scritto il programma.
 - <b>[Spring Initializr](https://start.spring.io/)</b>: con cui abbiamo creato la cartella iniziale del progetto, ci ha permesso di non dover definire e scrivere tutte le dipendenze a mano ma di avere già una base da cui partire.
 - <b>[JSON-Simple](https://code.google.com/archive/p/json-simple/)</b>: è la libreria che abbiamo usato per leggere e scrivere file e oggetti in formato JSON.
 - <b>[GitHub](https://github.com/)</b>: piattaforma dove abbiamo caricato il nostro programma.
 - <b>[GitHub Desktop](https://desktop.github.com/)</b>: che abbiamo usato per gestire tutta la parte relativa a commit / push / clone.
 - <b>[Postman](https://www.postman.com/)</b>: che abbiamo usato per effettuare le chiamate alle varie rotte del programma.
 - <b>[Spring Boot](https://spring.io/)</b>: un framework che oltre a configurare in modo "automatico" librerie e dipendenze della nostra applicazione, ci ha permesso di avere un web server Tomcat integrato sul quale poterla eseguire.

**<b>Documenti</b>**:
   - è disponibile per la consultazione il <b>[JavaDoc](https://github.com/LudovicoGo/Progetto_OOP_ESAME/tree/master/ProgettoEsame/javadoc)</b> completo del programma.


<a name="autori"></a>
## Autori
- Gorgoglione Ludovico
- Christian Curzi
