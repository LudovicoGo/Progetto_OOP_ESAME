# Progetto_OOP_ESAME

<a name="introduzione"></a>
## Introduzione
Questo programma ha come scopo quello di offrire all'utente (tramite chiamate alle API Current di [OpenWeather](https://openweathermap.org/)) la possibilità di ottenere previsioni meteo istantanee complete relative a una città e di poter calcolare delle statistiche relative a visibilità e umidità di una città, potendo anche scegliere il periodo di tempo su cui calcolarle. Le statistiche su umidità e visibilità vengono calcolate prendendo i valori necessari da dei file presenti nella cartella [dataExamples](https://github.com/LudovicoGo/Progetto_OOP_ESAME/tree/master/ProgettoEsame/src/main/resources/dataExamples) (attualmente sono presenti dei file di esempio, ma tali file possono anche essere generati usando questo programma).

## INDICE:
* [Introduzione](#introduzione)
* [Installazione e Configurazione](#installEConf)
* [Rotte](#rotte)
* [Eccezioni](#eccezioni)
* [Test effettuati](#test)
* [Autori](#autori)

<a name="installEConf"></a>
## Installazione e Configurazione
Il programma può essere installato clonandolo da questa directory tramite prompt dei comandi, usando [GitHub Desktop](https://desktop.github.com/) o direttamente da IDE per esempio da [Intellij](https://blog.jetbrains.com/idea/2020/10/clone-a-project-from-github/).

Nel programma è già presente una  ``` API Key ```  messa a vostra disposizione, è possibile sostituirla con una propria andando a modificare tale voce nella classe [WeatherServiceImpl](https://github.com/LudovicoGo/Progetto_OOP_ESAME/blob/master/ProgettoEsame/src/main/java/com/progetto/ProgettoEsame/service/WeatherServiceImpl).

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
| [/weather](#_1)          |     GET    | Restituisce un JSONObject contenente le previsioni meteo istantanee (ancheumidità e visibilità) della città richiesta.
| [/scheduledWeather](#_2) |     GET    | Salva su file JSON le previsioni del meteo complete di una città in modo ciclico, una volta all'ora, in un certo periodo di tempo indicato.
| [/visibility](#_3)       |     GET    | Calcola media e varianza della visibilità, relative ad un dato periodi di tempo, di una città prendendone i valori da un file JSON.
| [/humidity](#_4)         |     GET    | Calcola media e varianza della umidità, relative ad un dato periodi di tempo, di una città prendendone i valori da un file JSON.

### Spiegazione rotte
   <a name="_1"></a>
  - **<b>Utilizzo rotta /weather</b>**:

    Questa rotta prende in input il nome di una città e restituisce un JSONObject con dentro le previsioni meteo istantanee complete (comprese visibilità e umidità), si può inserire una città qualsiasi e nel caso la città inserita non esista o si sbagli a scriverne il nome verrà restituito un errore. Si utilizza cosi:
  
      ```
      localhost:8080/weather?cityName={NomeCittà}
      ```
      (mettere il nome della città richiesta al posto di "NomeCittà" e rimuovere le parentesi graffe)
      
  <a name="_2"></a>  
 - **<b>Utilizzo rotta /scheduledWeather</b>**:
 
    Questa rotta prende in input il nome di una città, un periodo di tempo, l'inizio e la fine di quel periodo del periodo di riferimento e salva su un file JSON le previsioni meteo istantanee complete (comprese visibilità e umidità), si può inserire una città qualsiasi. I periodi di tempo disponibili sono TimeSlot, Daily, ChosenDay, Weekly. Le date vanno inserite seguendo il formato ddMMyyyyHHmmss.
      I file avranno come nome "nome città + WeatherArray.json" e verranno salvati all'interno della cartella del progetto nel seguente percorso:  
      
      ```
      "src/main/resources/saved/NomeFile.json"
      ```
      
      Questa rotta si può usare in diversi modi a seconda del periodo di tempo scelto:
      
      - **<b>Daily</b>**: 
         Richiede come input necessari il nome della città e il periodo di tempo che deve essere scelto è "Daily", salva su un file le previsioni del meteo a partire da quando si invia la richiesta fino alla fine del giorno corrente, si può scrivere cosi:
         
         ```
         localhost:8080/scheduledWeather?cityName={NomeCittà}&period=Daily
         ```
         (mettere il nome della città richiesta al posto di "NomeCittà" e rimuovere le parentesi graffe)
         
         
      - **<b>TimeSlot</b>**: 
         Richiede come input necessari solo il nome della città, va scelto il periodo di tempo "TimeSlot", va fornita la data (e ora) del momento di inizio e della fine del salvataggio delle previsioni. Salva su un file le previsioni del meteo, ogni ora, di una <b>fascia oraria</b> definita inserendo l'ora d'inizio e quella di fine. Va usata cosi:
         
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
      I file devono avere come nome "nome città + periodo + WeatherArray.json", possono essere generati tramite l'uso della rotta /scheduledWeather (vedi sopra) e sono salvati all'interno della cartella del progetto nel seguente percorso:  
      
      ```
      "src/main/resources/dataExamples/NomeFile.json"
      ```


  <a name="_4"></a>           
 - **<b>Utilizzo rotta /humidity</b>**:
 
    Questa rotta prende in input il nome di una città e il nome di un periodo di tempo (TimeSlot, Weekly, ChosenDay, Daily), si può inserire una città tra quelle presenti nel database di esempi (ovvero: Milan, Valencia, London, Paris).
    Chiamando questa rotta il programma prende il file su cui sono salvati i dati del meteo della città scelta nel periodo scelto, ne estrapola i valori dell'umidità e li usa per calcolare media e varianza di quest'ultima.
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

  Viene lanciata quando si provano a salvare su file delle previsioni usando un periodo di tempo non ammesso dal programma.
  Viene lanciata nel [controller](https://github.com/LudovicoGo/Progetto_OOP_ESAME/blob/master/ProgettoEsame/src/main/java/com/progetto/ProgettoEsame/controller/WeatherController.java) dal metodo saveScheduledWeather, restituisce il seguente messaggio di errore:
  
   ```hai commesso un ERRORE nell'inserimento delle date/ore```
 
<a name="test"></a>
## Test effettuati   
- **<b>ServiceTest</b>**: sono test effettuati per verificare il corretto funzionamento dei metodi della classe <b>[WeatherServiceImpl](https://github.com/LudovicoGo/Progetto_OOP_ESAME/blob/master/ProgettoEsame/src/main/java/com/progetto/ProgettoEsame/service/WeatherServiceImpl.java)</b>, in particolare sono:
   - <b>shouldFindThatCity</b>:
        Test che va a verificare che la città di cui si ottengono le previsioni tramite il metodo <b>getJSONWeather</b> sia effettivamente quella richiesta dall'utente.
   - <b>shouldConvertDate</b>:
        Test che va a verificare il corretto funzionamento del metodo <b>DataConverter</b>.
 
 - **<b>StatTest</b>**: sono test effettuati per verificare il corretto funzionamento dei metodi della classe <b>[Statistics](https://github.com/LudovicoGo/Progetto_OOP_ESAME/blob/master/ProgettoEsame/src/main/java/com/progetto/ProgettoEsame/filterAndStats/Statistics.java)</b>, in particolare sono:
    - <b>shouldCalculateAverageAndVariance</b>: 
       Test che va a verificare il corretto funzionamento dei metodi <b>Average</b> e <b>Variance</b>, cioè se calcolano in modo corretto media e varianza di una serie di valori.
 
    - <b>shouldFindMaxMinValue</b>:  
       Test che va a verificare il corretto funzionamento dei metodi <b>MaxValue</b> e <b>MinValue</b>, cioè vede se riescono ad estrarre il valore minimo e massimo contenuti in un <b>Vector di Long</b> in modo corretto.

<a name="autori"></a>
## Autori
- Gorgoglione Ludovico 60%
- Christian Curzi 40%
  
